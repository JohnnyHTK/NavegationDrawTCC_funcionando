package com.example.martinsj.navegationdraw;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;

public class MyFTPClientFunctions {

	// Now, declare a public FTP client object.

	private static final String TAG = "MyFTPClientFunctions";
	public FTPClient mFTPClient = null;

	// Method to connect to FTP server:
	public boolean ftpConnect(String host, String username, String password,
                              int port) {
		try {
			mFTPClient = new FTPClient();
			// connecting to the host
			mFTPClient.connect(host, port);

			// now check the reply code, if positive mean connection success
			if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
				// login using username & password
				boolean status = mFTPClient.login(username, password);

				mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
				mFTPClient.enterLocalPassiveMode();

				return status;
			}
		} catch (Exception e) {
			Log.d(TAG, "Error: could not connect to host " + host);
		}
	return false;
	}

	/**
	 * mFTPClient: FTP client connection object (see FTP connection example)
	 * srcFilePath: source file path in sdcard desFileName: file name to be
	 * stored in FTP server desDirectory: directory path where the file should
	 * be upload to
	 */
	public boolean ftpUpload(String srcFilePath, String desFileName) {
		boolean status = false;
		try {
			FileInputStream srcFileStream = new FileInputStream(srcFilePath);

			status = mFTPClient.storeFile(desFileName, srcFileStream);

			srcFileStream.close();

			return status;
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "upload failed: " + e);
		}

		return status;
	}
}