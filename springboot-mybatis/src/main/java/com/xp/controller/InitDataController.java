package com.xp.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xp.dao.UserDao;
import com.xp.domain.PlayList;
import com.xp.util.BufferedRandomAccessFile;
import com.xp.util.FileUtil;
import com.xp.util.RandomAccessFileTest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xp-zhao on 2017/9/29.
 */
@RestController
@RequestMapping("/init")
public class InitDataController
{
	private static final Logger logger   = Logger.getLogger(RandomAccessFileTest.class);

	private final     String ENCODING = "UTF-8";
	private final     int    NUM      = 50000;

	private           File   file     = new File("D:\\migu-userGroup\\data\\org\\test.txt");

	@Autowired
	private UserDao userDao;

	private PlayList playList = new PlayList();

	private List<PlayList> playLists = new ArrayList<>();

	@RequestMapping("/")
	public void init()
	{
		long start = System.currentTimeMillis();

		logger.info(String.valueOf(file.exists()));
		long pos = 0L;
		int count = 0;
		while (true) {
			Map<String, Object> res = BufferedRandomAccessFileReadLine(file, ENCODING, pos, NUM);
			// 如果返回结果为空结束循环
			if (MapUtils.isEmpty(res)) {
				break;
			}
			List<String> pins = (List<String>) res.get("pins");
			count = count + Integer.parseInt(res.get("count").toString());
			if(count == 10000000)
			{
				break;
			}
			if (CollectionUtils.isNotEmpty(pins)) {
				if (pins.size() < NUM) {
					break;
				}
			} else {
				break;
			}
			pos = (Long) res.get("pos");
		}
		logger.info(((System.currentTimeMillis() - start) / 1000) + ": "+count);
	}

	/**
	 * 通过BufferedRandomAccessFile读取文件,推荐
	 *
	 * @param file     源文件
	 * @param encoding 文件编码
	 * @param pos      偏移量
	 * @param num      读取量
	 * @return pins文件内容，pos当前偏移量
	 */
	public Map<String, Object> BufferedRandomAccessFileReadLine(File file, String encoding, long pos, int num) {
		Map<String, Object> res = Maps.newHashMap();
		List<String> pins = Lists.newArrayList();
		int count = 0;
		res.put("pins", pins);
		BufferedRandomAccessFile reader = null;
		try {
			reader = new BufferedRandomAccessFile(file, "r");
			reader.seek(pos);
			for (int i = 0; i < num; i++) {
				String pin = reader.readLine();
				if (StringUtils.isBlank(pin)) {
					break;
				}
				analyte(pin);
				playLists.add(playList);
				if(CollectionUtils.isNotEmpty(playLists) && playLists.size() % 500 == 0)
				{
					userDao.addData(playLists);
					playLists.clear();
				}
				count++;
				pins.add(new String(pin.getBytes("8859_1"), encoding));
			}
			res.put("pos", reader.getFilePointer());
			res.put("count",count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return res;
	}

	public void analyte(String line)
	{
		String[] data = line.split("#");
		playList.setPlaylistId(data[1]);
	}
}
