package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class WebUtils {

	public static <T> T upload(HttpServletRequest request, Class<T> clazz) {
		ServletFileUpload fileUpload = new ServletFileUpload(
				new DiskFileItemFactory());

		try {

			T t = clazz.newInstance();
			List<FileItem> fileItems = fileUpload.parseRequest(request);

			ConvertUtils.register(new DateLocaleConverter(), Date.class);

			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("utf-8");
					BeanUtils.setProperty(t, fieldName, fieldValue);
				} else {

					String fileName = fileItem.getName();
					BufferedInputStream input = new BufferedInputStream(
							fileItem.getInputStream());
					fileName = UUID.randomUUID()
							+ fileName.substring(fileName.lastIndexOf("."));
					String filePath = "/image/" + fileName;
					String realPath = request.getSession().getServletContext()
							.getRealPath(filePath);

					FileOutputStream output = new FileOutputStream(new File(
							realPath));
					IOUtils.copy(input, output);
					fileItem.delete();

					BeanUtils.setProperty(t, fileItem.getFieldName(), filePath);
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanClass) {

		try {

			T t = beanClass.newInstance();

			// 注册转换器
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			// 封装
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(t, name, value);
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}
}
