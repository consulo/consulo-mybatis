package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.SubTagList;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 22.01.12
 * Time: 0:34
 */
public interface Insert extends SqlMapIdentifiableStatement
{
	@SubTagList("selectKey")
	List<SelectKey> getSelectKeys();
}
