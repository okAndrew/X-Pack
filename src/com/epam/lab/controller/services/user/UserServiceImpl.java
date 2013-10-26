package com.epam.lab.controller.services.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.MailSender;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Role;
import com.epam.lab.model.Token;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class UserServiceImpl extends AbstractServiceImpl<User> implements
		UserService {

	private UserDAOImpl userDaoImpl = new UserDAOImpl();
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
		super(new UserDAOImpl());
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

	public User getUser(String email, String password) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public User getUserByEmail(String email) {
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
		long[] files = new long[filesId.length];
		long[] folders = new long[foldersId.length];
		
		for (int i = 0; i < filesId.length; i++) {
			try {
				files[i] = Long.valueOf(filesId[i]);
			} catch (NumberFormatException e) {
				logger.error(e);
			}
		}
		
		for (int i = 0; i < filesId.length; i++) {
			try {
				folders[i] = Long.valueOf(foldersId[i]);
			} catch (NumberFormatException e) {
				logger.error(e);
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
	public void deleteUsers(String[] usersId) {
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();
		for (int i = 0; i < usersId.length; i++) {
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
	public void activateUsers(String[] usersId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		for (int i = 0; i < usersId.length; i++) {
			userDaoImpl.setIsActivate(true, Long.parseLong(usersId[i]));
			MailSender.send(get(Long.parseLong(usersId[i])).getEmail(),
					"Activation", "You're activated!!!");
		}
	}

	@Override
	public void banedUsers(String[] usersId) {
		for (int i = 0; i < usersId.length; i++) {
			userDaoImpl.setIsBanned(true, Long.parseLong(usersId[i]));
			MailSender.send(get(Long.parseLong(usersId[i])).getEmail(), "Ban",
					"You're baned!!!");
		}
	}

	@Override
	public void cancelBanUsers(String[] usersId) {
		for (int i = 0; i < usersId.length; i++) {
			userDaoImpl.setIsBanned(false, Long.parseLong(usersId[i]));
			MailSender.send(get(Long.parseLong(usersId[i])).getEmail(),
					"Cancel ban", "You're not baned!!!");
		}

	}

	public void changeUserPassword(User user, String password) {
		MD5Encrypter md5 = new MD5Encrypter();
		String md5Pass = md5.encrypt(password);
		UserDAOImpl userDaoImpl = new UserDAOImpl();

		user.setPassword(md5Pass);
		userDaoImpl.update(user);
	}

	public String sendUsersEmail(String[] usersId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		String errorMessage = null;
		List<User> users = new ArrayList<User>();
		if (usersId == null) {
			errorMessage = "Please check the users you want to send email!!!";
		} else {
			for (int i = 0; i < usersId.length; i++) {
				users.add(userDaoImpl.get(Long.parseLong(usersId[i])));
			}
			for (User iter : users) {
				MailSender.send(iter.getEmail(), "dreamhost", "test message");
			}
		}
		return errorMessage;
	}

	public User changeUserLogin(String email, String login) {
		User user = getUserByEmail(email);

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
			user = getUserByEmail(oldEmail);
			StringBuilder msg = new StringBuilder();
			String head = "Activation";
			String token = createToken(user);

			msg.append("http://localhost:8080/dreamhost/edituseremail");
			msg.append("?oldEmail=").append(oldEmail);
			msg.append("&newEmail=").append(newEmail);
			msg.append("&token=").append(token);

			MailSender.send(user.getEmail(), head, msg.toString());
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

			User user = getUserByEmail(oldEmail);
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
			List<Payment> tempPayments = paymentService.getAvailableUserPays(tempUser.getId());
			if (tempPayments != null && tempPayments.get(0).getDateEnd().before(TimeStampManager.getCurrentTime())) {
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
			Payment payment = paymentService.getLastUserPayment(users.get(i).getId());
			if (payment != null && TimeStampManager.addNumberOfMonth(payment.getDateEnd(), 1).before(TimeStampManager.getCurrentTime())) {
				List<UserFile> files = fileService.getByUserId(users.get(i).getId());
				List<Folder> folders = folderService.getAll(users.get(i).getId());
				
				deleteFilesAndFolders(
					files.toArray(new UserFile[files.size()]),
					folders.toArray(new Folder[folders.size()])
				);
				
				users.get(i).setIdTariff(0);
				users.get(i).setIsBanned(false);
				update(users.get(i));
			}
		}
	}

}