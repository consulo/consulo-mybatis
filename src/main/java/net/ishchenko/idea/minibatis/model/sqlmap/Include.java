package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.Attribute;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.GenericAttributeValue;
import consulo.xml.util.xml.NameValue;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.01.12
 * Time: 20:40
 */
public interface Include extends DomElement
{

	@NameValue
	@Attribute("refid")
	GenericAttributeValue<SqlMapIdentifiableStatement> getRefid();
}
