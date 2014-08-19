package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

/**
 * 关于文件常用的操作
 * 
 * @author LeeFranker
 * 
 */
public class FileUtils {
	private static final int IO_BUFFER_SIZE = 8 * 1024; // 8k
	private static final int BUFFER_SIZE = 2 * 1024;// 2K

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileDir, String fileName) throws IOException {
		File file = new File(fileDir, fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param fileDir
	 * @return
	 */
	public static File createDir(String fileDir) {
		File file = new File(fileDir);
		file.mkdir();
		return file;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileDir, String fileName) {
		File file = new File(fileDir, fileName);
		return file.exists();
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists())
			return file.delete();
		else
			return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileDir, String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists())
			return file.delete();
		else
			return false;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param fileDir
	 * @return
	 */
	public static boolean deleteDirectory(String fileDir) {
		if (TextUtils.isEmpty(fileDir))
			return false;
		if (!fileDir.endsWith(File.separator))
			fileDir += File.separator;
		File dirFile = new File(fileDir);
		if (!dirFile.isDirectory() || !dirFile.exists())
			return false;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile())
				deleteFile(files[i].getAbsolutePath());
			else
				deleteDirectory(files[i].getAbsolutePath());
		}
		if (dirFile.delete())
			return true;
		else
			return false;
	}

	/**
	 * 拷贝文件
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			File oldFile = new File(oldPath);
			if (oldFile.exists()) {
				FileInputStream input = new FileInputStream(oldFile);
				FileOutputStream out = new FileOutputStream(newPath);
				int byteRead = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((byteRead = input.read(buffer)) != -1) {
					out.write(buffer, 0, byteRead);
				}
				out.flush();
				out.close();
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyDir(String oldPath, String newPath) {
		try {
			File file = new File(newPath);
			if (!file.exists())
				file.mkdir();
			String[] names = file.list();
			File temp = null;
			for (int i = 0; i < names.length; i++) {
				if (!oldPath.endsWith(File.separator))
					temp = new File(oldPath + File.separator + names[i]);
				else
					temp = new File(oldPath + names[i]);
				if (temp.isFile()) {
					FileInputStream fis = new FileInputStream(temp);
					FileOutputStream out = new FileOutputStream(newPath + File.separator + temp.getName().toString());
					byte[] buffer = new byte[BUFFER_SIZE];
					int len = 0;
					while ((len = fis.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.flush();
					out.close();
					fis.close();
				}
				if (temp.isDirectory())
					copyDir(oldPath + File.separator + names[i], newPath + File.separator + names[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把字符串写入文件里面
	 * 
	 * @param file
	 * @param content
	 */
	public static void writeStr2File(File file, String string) {
		FileOutputStream os = null;
		try {
			byte[] buffer = string.getBytes("UTF-8");
			os = new FileOutputStream(file);
			os.write(buffer);
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
	}

	/**
	 * 把字符串写入文件里面
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void writeStr2File(String fileName, String string) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		File distFile = new File(fileName);
		try {
			if (distFile.exists()) {
				distFile.delete();
			}
			distFile.createNewFile();
			bufferedReader = new BufferedReader(new StringReader(string));
			bufferedWriter = new BufferedWriter(new FileWriter(distFile));
			char buf[] = new char[BUFFER_SIZE];
			int len;
			while ((len = bufferedReader.read(buf)) != -1) {
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中读取字符串
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readStrFormFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuilder builder = new StringBuilder();
			while (reader.ready()) {
				builder.append(reader.readLine());
			}
			reader.close();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 遍历文件夹获取文件列表
	 * 
	 * @param fileDir
	 * @param list
	 * @param suffix
	 * @return
	 */
	public static List<File> searchFileBySuffix(String fileDir, List<File> list, String... suffix) {
		if (null == list)
			list = new ArrayList<File>();
		File file = new File(fileDir);
		if (file.isDirectory()) {
			File[] childs = file.listFiles();
			if (childs != null)
				for (int i = 0; i < childs.length; i++) {
					if (childs[i].isDirectory()) {
						searchFileBySuffix(childs[i].getAbsolutePath(), list, suffix);
					} else {
						for (int j = 0; j < suffix.length; j++) {
							if (childs[i].getName().endsWith(suffix[j])) {
								list.add(childs[i]);
							}
						}

					}
				}
		}
		return list;
	}

	/**
	 * 文件是否保存成功
	 * 
	 * @param content
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static boolean getIsFileSaveSuccess(InputStream content, String fileDir, String fileName) {
		FlushedInputStream is = null;
		FileOutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			deleteFile(fileDir, fileName);
			File file = createFile(fileDir, fileName);
			is = new FlushedInputStream(new BufferedInputStream(content, IO_BUFFER_SIZE));
			os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os, IO_BUFFER_SIZE);
			int byteRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((byteRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, byteRead);
			}
			bos.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		deleteFile(fileDir, fileName);
		return false;
	}

	private static class FlushedInputStream extends FilterInputStream {

		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int by_te = read();
					if (by_te < 0) {
						break;
					} else {
						bytesSkipped = 1;
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}

	/**
	 * 获取文件的大小
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSize(String fileName) {
		File file = new File(fileName);
		if (!file.exists())
			return null;
		long length = file.length();
		float SIZE_BT = 1024L;
		float SIZE_KB = SIZE_BT * 1024.0f;
		float SIZE_MB = SIZE_KB * 1024.0f;
		float SIZE_GB = SIZE_MB * 1024.0f;
		int SACLE = 2;
		if (length >= 0 && length < SIZE_BT) {
			return (double) (Math.round(length * 10) / 10.0) + "B";
		} else if (length >= SIZE_BT && length < SIZE_KB) {
			return (double) (Math.round((length / SIZE_BT) * 10) / 10.0) + "KB";
		} else if (length >= SIZE_KB && length < SIZE_MB) {
			return (double) (Math.round((length / SIZE_KB) * 10) / 10.0) + "MB";
		} else if (length >= SIZE_MB && length < SIZE_GB) {
			BigDecimal longs = new BigDecimal(Double.valueOf(length + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "").toString());
			String result = longs.divide(sizeMB, SACLE, BigDecimal.ROUND_HALF_UP).toString();
			return result + "GB";
		} else {
			BigDecimal longs = new BigDecimal(Double.valueOf(length + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "").toString());
			String result = longs.divide(sizeMB, SACLE, BigDecimal.ROUND_HALF_UP).toString();
			return result + "TB";
		}
	}

	/**
	 * 将File转换为byte[]
	 * 
	 * @param file
	 * @return
	 * @throws
	 */
	public static byte[] file2byte(File file) {
		try {
			InputStream is = new FileInputStream(file);
			long length = file.length();
			byte[] bytes = new byte[(int) length];
			// 读取数据到byte数组中
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			is.close();
			// 确保所有数据均被读取
			if (offset < bytes.length)
				return null;
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将byte[]转换为File
	 * 
	 * @param bytes
	 * @param file
	 * @return
	 */
	public static boolean byte2file(byte[] bytes, File file) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
