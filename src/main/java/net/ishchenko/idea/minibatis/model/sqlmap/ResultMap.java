package net.ishchenko.idea.minibatis.model.sqlmap;

import java.util.List;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.SubTagList;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 22.01.12
 * Time: 0:28
 */
public interface ResultMap extends DomElement
{

	@NameValue
	@Attribute("id")
	GenericAttributeValue<String> getId();

	@Attribute("class")
	GenericAttributeValue<TypeAlias> getClazz();

	@SubTagList("result")
	List<Result> getResults();

}
