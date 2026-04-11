package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.dom.Attribute;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.GenericAttributeValue;
import consulo.xml.dom.NameValue;

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
