package net.ishchenko.idea.minibatis;

import javax.annotation.Nonnull;

import com.intellij.patterns.PsiJavaPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.ProcessingContext;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 17.12.11
 * Time: 20:36
 */
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

}

