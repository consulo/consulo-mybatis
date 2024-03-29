package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.xml.util.xml.*;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 23.12.11
 * Time: 23:43
 */
public interface SqlMapIdentifiableStatement extends DomElement
{

	@NameValue
	@Attribute("id")
	GenericAttributeValue<String> getId();

	@Attribute("parameterClass")
	GenericAttributeValue<TypeAlias> getParameterClass();

	@Attribute("parameterMap")
	GenericAttributeValue<ParameterMap> getParameterMap();

	@SubTagList("include")
	List<Include> getIncludes();

}
