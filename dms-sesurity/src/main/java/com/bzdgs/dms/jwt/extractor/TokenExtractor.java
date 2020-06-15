package com.bzdgs.dms.jwt.extractor;

/**
 * @ClassName: TokenExtractor
 * @Description: 我们应该总是返回原Base-64编码JWT标记表示
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
public interface TokenExtractor {

	/**
	 * 提取token
	 * @param payload
	 * @return
	 */
	public String extract(String payload);
}
