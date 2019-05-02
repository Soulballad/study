package com.gupao.source.spring.mock.demo.service.impl;

import com.gupao.source.spring.mock.demo.service.IQueryService;
import com.gupao.source.spring.mock.springfamework.annotation.GPService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询业务
 * @author Tom
 *
 */
@GPService
@Slf4j
public class QueryService implements IQueryService {

	/**
	 * 查询
	 */
	public String query(String name) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
			log.info("这是在业务方法中打印的：" + json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
