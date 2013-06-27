package net.ishchenko.idea.minibatis.model.sqlmap;

import java.util.List;

import com.intellij.util.xml.SubTagList;

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
