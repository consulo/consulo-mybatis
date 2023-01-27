package net.ishchenko.idea.minibatis.model.sqlmap;

import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReference;
import consulo.xml.util.xml.*;
import net.ishchenko.idea.minibatis.ResultMapReference;

import javax.annotation.Nonnull;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 10.04.12
 * Time: 22:53
 */
public interface Result extends DomElement
{
	@NameValue
	@Attribute("property")
	GenericAttributeValue<String> getProperty();

	@Attribute("typeHandler")
	GenericAttributeValue<TypeAlias> getTypeHandler();

	@Attribute("resultMap")
	@Referencing(ResultMapReferenceConverter.class)
	GenericAttributeValue<ResultMap> getResultMap();

	class ResultMapReferenceConverter implements CustomReferenceConverter
	{

		@Nonnull
		@Override
		public PsiReference[] createReferences(GenericDomValue genericDomValue, PsiElement element, ConvertContext context)
		{
			return new PsiReference[]{new ResultMapReference(element)};
		}

	}

}
