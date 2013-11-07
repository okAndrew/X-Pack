package com.epam.lab.controller.services.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.locale.LocaleServiceImpl;
import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.MailSender;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Locale;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Role;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.Token;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class UserServiceImpl extends AbstractServiceImpl<User> implements
		UserService {

	private UserDAOImpl userDaoImpl = new UserDAOImpl();
	private MailSender sender = new MailSender();
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
		super(new UserDAOImpl());
	}

	public long getCount() {
		return userDaoImpl.getCount();
	}

	public void addUser(String login, String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		user.setIdTariff(6);
		user.setCapacity(0);
		user.setRole(Role.USER);
		user.setIsBanned(false);
		insert(user);
	}

	public List<User> getBySQL(String sql) {
		return userDaoImpl.getBySQL(sql);
	}

	public User get(String email, String password) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public User get(String email) {
		return new UserDAOImpl().getByEmail(email);
	}

	public void deleteFilesAndFolders(long[] filesId, long[] foldersId) {
		FolderServiceImpl folderService = new FolderServiceImpl();
		UserFileServiceImpl fileService = new UserFileServiceImpl();

		if (filesId != null && filesId.length > 0) {
			for (int i = 0; i < filesId.length; i++) {
				fileService.delete(filesId[i]);
			}
		}

		if (foldersId != null && foldersId.length > 0) {
			for (int i = 0; i < foldersId.length; i++) {
				folderService.delete(foldersId[i]);
			}
		}
	}

	public void deleteFilesAndFolders(String[] filesId, String[] foldersId) {
		long[] files = null;
		long[] folders = null;
		if (filesId != null) {
			files = new long[filesId.length];
			for (int i = 0; i < filesId.length; i++) {
				try {
					files[i] = Long.valueOf(filesId[i]);
				} catch (NumberFormatException e) {
					logger.error(e);
				}
			}
		}
		if (foldersId != null && !foldersId[0].equals("")) {
			folders = new long[foldersId.length];
			for (int i = 0; i < foldersId.length; i++) {
				try {
					folders[i] = Long.valueOf(foldersId[i]);
				} catch (NumberFormatException e) {
					logger.error(e);
				}
			}
		}
		deleteFilesAndFolders(files, folders);
	}

	public void deleteFilesAndFolders(UserFile[] files, Folder[] folders) {
		long[] filesId = new long[files.length];
		long[] foldersId = new long[folders.length];

		for (int i = 0; i < files.length; i++) {
			filesId[i] = files[i].getId();
		}

		for (int i = 0; i < folders.length; i++) {
			foldersId[i] = folders[i].getId();
		}

		deleteFilesAndFolders(filesId, foldersId);
	}

	@Override
	public void deleteUsers(String[] usersId, Long idAdmin) {
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();
		for (int i = 0; i < usersId.length; i++) {
			if (Long.parseLong(usersId[i]) == idAdmin) {
				continue;
			}
			fileService.deleteByUserId(Long.parseLong(usersId[i]));
			folderService.deleteByUserId(Long.parseLong(usersId[i]));
			userDaoImpl.delete((Long.parseLong(usersId[i])));
		}
	}

	public int activateUser(User user) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int result = userDaoImpl.setIsActivate(true, user.getId());
		return result;
	}

	@Override
	public void activateUsers(String[] usersId, Long idAdmin) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		for (int i = 0; i < usersId.length; i++) {
			if (Long.parseLong(usersId[i]) == idAdmin) {
				continue;
			}
			userDaoImpl.setIsActivate(true, Long.parseLong(usersId[i]));
			sender.send(get(Long.parseLong(usersId[i])).getEmail(),
					"Activation", "You're activated!!!");
		}
	}

	@Override
	public void banedUsers(String[] usersId, Long idAdmin) {
		for (int i = 0; i < usersId.length; i++) {
			if (Long.parseLong(usersId[i]) == idAdmin) {
				continue;
			}
			userDaoImpl.setIsBanned(true, Long.parseLong(usersId[i]));
			sender.send(get(Long.parseLong(usersId[i])).getEmail(), "Ban",
					"You're baned!!!");
		}
	}

	@Override
	public void cancelBanUsers(String[] usersId, Long idAdmin) {
		for (int i = 0; i < usersId.length; i++) {
			if (Long.parseLong(usersId[i]) == idAdmin) {
				continue;
			}
			userDaoImpl.setIsBanned(false, Long.parseLong(usersId[i]));
			sender.send(get(Long.parseLong(usersId[i])).getEmail(),
					"Cancel ban", "You're not baned!!!");
		}

	}

	@Override
	public void deleteFiles(String[] filesId, Long userId, String message) {
		StringBuilder deletedFiles = new StringBuilder();
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();
		int i = 1;
		if (filesId != null) {
			for (String fileId : filesId) {
				UserFile file = fileService.get(Long.parseLong(fileId));

				deletedFiles.append("  ").append(i + ". ")
						.append(file.getNameIncome());
				fileService.delete(Long.parseLong(fileId));
				Folder folder = folderService.get(file.getIdFolder());
				if (folder.getSize() == 0 && !(folder.getName().equals("root"))) {
					folderService.delete(folder.getId());
				}
				i++;
			}
			StringBuilder str = new StringBuilder();
			str.append("Your files: ").append(deletedFiles)
					.append("  were deleted from Dreamhost, because of ")
					.append(message);
			sender.send(get(userId).getEmail(), "Deleted files", str.toString());

		}

	}

	public String changeUserPassword(long userId, String oldPassword,
			String newPassword, String newPasswordRe) {
		String msg = null;
		MD5Encrypter md5 = new MD5Encrypter();
		User user = get(userId);

		if (user != null) {
			if (newPassword.equals(newPasswordRe)) {
				oldPassword = md5.encrypt(oldPassword);
				if (user.getPassword().equals(oldPassword)) {
					newPassword = md5.encrypt(newPassword);
					user.setPassword(newPassword);
					update(user);
				} else {
					msg = "Old_password_is_incorect";
				}
			} else {
				msg = "New_passwords_are_different";
			}
		} else {
			msg = "Internal_error._Reload_page.";
		}

		return msg;
	}

	public void sendUsersEmail(String[] usersId, String subject, String message) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < usersId.length; i++) {
			users.add(userDaoImpl.get(Long.parseLong(usersId[i])));
		}
		for (User iter : users) {
			sender.send(iter.getEmail(), subject, message);
		}
	}

	public User changeUserLogin(String email, String login) {
		User user = get(email);

		if (user != null) {
			Validator.USER_LOGIN.validate("asd");
			user.setLogin(login);
			update(user);
		}

		return user;
	}

	public boolean checkEmailById(String email, long userId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		boolean result = userDaoImpl.checkEmailById(email, userId);
		return result;
	}

	public boolean ckeckLoginById(String login, long userId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		boolean result = userDaoImpl.ckeckLoginById(login, userId);
		return result;
	}

	public User getUserByFolderId(long idFolder) throws FolderNotFoundException {
		Folder folder = new FolderDAOImpl().get(idFolder);
		if (folder == null)
			throw new FolderNotFoundException();
		return new UserServiceImpl().get(folder.getIdUser());
	}

	public boolean editLogin(String email, String login) {
		boolean res = false;

		if (Validator.USER_LOGIN.validate(login)
				&& Validator.USER_EMAIL.validate(email)) {
			UserDAOImpl userDaoImpl = new UserDAOImpl();
			User user = userDaoImpl.getByEmail(email);
			user.setLogin(login);

			if (userDaoImpl.update(user) == 1) {
				res = true;
			}
		}

		return res;
	}

	public boolean tryEditEmail(String oldEmail, String newEmail) {
		boolean res = false;
		User user = null;

		if (Validator.USER_EMAIL.validate(oldEmail)
				&& Validator.USER_EMAIL.validate(newEmail)) {
			user = get(oldEmail);
			StringBuilder msg = new StringBuilder();
			String head = "Activation";
			String token = createToken(user);

			msg.append("http://localhost:8080/dreamhost/edituseremail");
			msg.append("?oldEmail=").append(oldEmail);
			msg.append("&newEmail=").append(newEmail);
			msg.append("&token=").append(token);

			sender.send(user.getEmail(), head, msg.toString());
			res = true;
		}

		return res;
	}

	private String createToken(User user) {
		Token token = new Token();
		MD5Encrypter md5 = new MD5Encrypter();
		String sToken = null;
		Timestamp timestamp = TimeStampManager.getCurrentTime();

		sToken = md5.encrypt(user.getEmail() + timestamp);

		token.setIdUser(user.getId());
		token.setDate(timestamp);
		token.setToken(sToken);

		new TokenDAOImpl().insert(token);

		return sToken;
	}

	public boolean editUserEmail(String oldEmail, String newEmail, String token) {
		boolean res = false;
		TokenDAOImpl tokenDAO = new TokenDAOImpl();

		if (Validator.MD5_CHECKSUM.validate(token)
				&& Validator.USER_EMAIL.validate(oldEmail)
				&& Validator.USER_EMAIL.validate(newEmail)) {

			User user = get(oldEmail);
			Token tokenObj = tokenDAO.get(token);

			if (tokenObj != null && tokenObj.getAvailable()
					&& tokenObj.getIdUser() == user.getId()) {
				user.setEmail(newEmail);
				tokenObj.setAvailable(false);

				update(user);
				tokenDAO.update(tokenObj);
				res = true;
			} else {
				res = false;
			}
		}

		return res;
	}

	public void deactivateOverdue() {
		UserServiceImpl userService = new UserServiceImpl();
		PaymentServiceImpl paymentService = new PaymentServiceImpl();

		List<Payment> payments = paymentService.getAvailableEndedPayments();
		for (int i = 0; i < payments.size(); i++) {
			User tempUser = userService.get(payments.get(i).getUser());
			List<Payment> tempPayments = paymentService
					.getAvailableUserPays(tempUser.getId());
			if (tempPayments != null
					&& tempPayments.get(0).getDateEnd()
							.before(TimeStampManager.getCurrentTime())) {
				tempUser.setIsBanned(true);
				payments.get(i).setAvailable(false);
				userService.update(tempUser);
				paymentService.update(payments.get(i));
			}
		}
	}

	public void setUsersForFree() {
		UserDAOImpl userDAO = new UserDAOImpl();
		PaymentServiceImpl paymentService = new PaymentServiceImpl();
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();

		List<User> users = userDAO.getBannedUsers();

		for (int i = 0; i < users.size(); i++) {
			Payment payment = paymentService.getLastUserPayment(users.get(i)
					.getId());
			if (payment != null
					&& TimeStampManager.addNumberOfMonth(payment.getDateEnd(),
							1).before(TimeStampManager.getCurrentTime())) {
				List<UserFile> files = fileService.getByUserId(users.get(i)
						.getId());
				List<Folder> folders = folderService.getAll(users.get(i)
						.getId());

				deleteFilesAndFolders(
						files.toArray(new UserFile[files.size()]),
						folders.toArray(new Folder[folders.size()]));

				users.get(i).setIdTariff(0);
				users.get(i).setIsBanned(false);
				update(users.get(i));
			}
		}
	}

	public boolean isBanned(long userId) {
		return userDaoImpl.get(userId).getIsBanned();
	}

	public void moveFilesAndFolders(String[] moveAble, long idTarget,
			long userId) {
		for (String move : moveAble) {
			String type = move.split("-")[0];
			long id = Long.parseLong(move.split("-")[1]);
			if (type.equals("file")) {
				UserFileServiceImpl fileService = new UserFileServiceImpl();
				if (fileService.isUsersFile(id, userId)) {
					fileService.moveFile(id, idTarget);
				}
			} else if (type.equals("folder")) {
				FolderServiceImpl folderService = new FolderServiceImpl();
				if (folderService.isUsersFolder(id, userId) && id != idTarget) {
					folderService.movefolder(id, idTarget);
				}
			}
		}
	}

	public long getFreeSize(long userId) {
		UserDAOImpl dao = new UserDAOImpl();
		User user = dao.get(userId);
		TariffDAOImpl tariffDao = new TariffDAOImpl();
		Tariff tarriff = tariffDao.get(user.getIdTariff());
		long freeSize = tarriff.getMaxCapacity() - user.getCapacity();
		return freeSize;

	}

	public String checkUpdate(String userEmail, int userId, String userLogin,
			boolean activated, boolean banned, Role role) {
		String errmessage = null;
		if (checkEmailById(userEmail, userId)) {
			if (ckeckLoginById(userLogin, userId)) {
				if (Validator.USER_LOGIN.validate(userLogin)) {
					User user = get(userId).setLogin(userLogin)
							.setEmail(userEmail).setIsActivated(activated)
							.setIsBanned(banned).setRole(role);
					userDaoImpl.update(user);
				} else {
					errmessage = "Login_must_be_string_without_spesial_characters";
					return errmessage;
				}
			} else {
				errmessage = "Login_already_exists";
				return errmessage;
			}
		} else {
			errmessage = "Email_already_exists";
			return errmessage;

		}
		return errmessage;
	}

	@Override
	public void setLastLocale(String locale, long userId) {
		LocaleServiceImpl locImpl = new LocaleServiceImpl();
		Locale localeObj = locImpl.getByLocale(locale.toString());
		UserDAOImpl ui = new UserDAOImpl();
		ui.setLastLocale(localeObj.getId(), userId);
	}

	public void refresh(long userId) {
		FolderService folderService = new FolderServiceImpl();
		folderService.refresh(userId);
		User user = this.userDaoImpl.get(userId);
		user.setCapacity(folderService.getRoot(userId).getSize());
		this.userDaoImpl.update(user);
	}
}