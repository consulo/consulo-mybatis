package net.ishchenko.idea.minibatis;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.patterns.PsiJavaPatterns;
import com.intellij.java.language.psi.PsiLiteral;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.psi.*;
import consulo.language.util.ProcessingContext;

import javax.annotation.Nonnull;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 17.12.11
 * Time: 20:36
 */
@ExtensionImpl
public class MyBatisReferenceContributor extends PsiReferenceContributor
{
	@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar)
	{

		registrar.registerReferenceProvider(PsiJavaPatterns.psiLiteral(), new PsiReferenceProvider()
		{
			@Nonnull
			public PsiReference[] getReferencesByElement(@Nonnull PsiElement element, @Nonnull ProcessingContext context)
			{
				return new PsiReference[]{
						new IdentifiableStatementReference((PsiLiteral) element),
						new SqlMapReference((PsiLiteral) element)
				};
			}
		});

	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JavaLanguage.INSTANCE;
	}
}

