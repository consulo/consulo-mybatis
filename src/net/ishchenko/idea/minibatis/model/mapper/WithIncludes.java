package net.ishchenko.idea.minibatis.model.mapper;

import java.util.List;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

/**
 * Created with IntelliJ IDEA.
 * User: mishchenko
 * Date: 26.05.12
 * Time: 15:47
 */
public interface WithIncludes extends DomElement
{

	@SubTagList("include")
	List<Include> getIncludes();

}
