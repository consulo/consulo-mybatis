package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.01.12
 * Time: 21:54
 */
public interface ResultProvider extends DomElement
{
	@Attribute("resultClass")
	GenericAttributeValue<TypeAlias> getResultClass();

	@Attribute("resultMap")
	GenericAttributeValue<ResultMap> getResultMap();
}
