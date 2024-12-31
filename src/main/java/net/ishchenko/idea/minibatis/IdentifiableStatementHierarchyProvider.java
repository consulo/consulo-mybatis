package net.ishchenko.idea.minibatis;

import com.intellij.java.impl.ide.hierarchy.call.JavaCallHierarchyProvider;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.dataContext.DataContext;
import consulo.language.Language;
import consulo.language.editor.CommonDataKeys;
import consulo.language.editor.LangDataKeys;
import consulo.language.editor.hierarchy.CallHierarchyProvider;
import consulo.language.editor.hierarchy.HierarchyBrowser;
import consulo.language.editor.hierarchy.HierarchyProvider;
import consulo.language.editor.hint.HintManager;
import consulo.language.pom.PomTarget;
import consulo.language.pom.PomTargetPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReference;
import consulo.language.psi.search.ReferencesSearch;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.navigation.NavigationItem;
import consulo.project.Project;
import consulo.xml.lang.xml.XMLLanguage;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.DomTarget;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMapIdentifiableStatement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Max
 * Date: 02.05.13
 * Time: 21:40
 */
@ExtensionImpl
public class IdentifiableStatementHierarchyProvider implements CallHierarchyProvider
{
	private HierarchyProvider delegate = new JavaCallHierarchyProvider();

	@Nullable
	@Override
	public PsiElement getTarget(@Nonnull DataContext dataContext)
	{

		final Project project = dataContext.getData(CommonDataKeys.PROJECT);
		if(project == null)
			return null;

		Editor editor = dataContext.getData(LangDataKeys.EDITOR);
		assert editor != null;

		PsiElement psiElement = dataContext.getData(LangDataKeys.PSI_ELEMENT);
		if(!isIdentifiableStatement(psiElement))
		{
			return null;
		}
		assert psiElement != null;
		Collection<PsiReference> refs = ReferencesSearch.search(psiElement).findAll();
		if(refs.size() == 1)
		{
			PsiElement element = refs.iterator().next().getElement();
			PsiMethod parentMethod = PsiTreeUtil.getParentOfType(element, PsiMethod.class, true);
			if(parentMethod == null)
			{
				HintManager.getInstance().showErrorHint(editor, "No direct usages in methods found, no hierarchy can be built");
				return null;
			}
			else
			{
				return parentMethod;
			}
		}
		else
		{
			HintManager.getInstance().showErrorHint(editor, "Multiple usages found, no hierarchy can be built");
			return null;
		}

	}

	@Nonnull
	@Override
	public HierarchyBrowser createHierarchyBrowser(PsiElement target)
	{
		if(target instanceof NavigationItem && ((NavigationItem) target).canNavigate())
		{
			((NavigationItem) target).navigate(false);
		}
		return delegate.createHierarchyBrowser(target);
	}

	@Override
	public void browserActivated(@Nonnull HierarchyBrowser hierarchyBrowser)
	{
		delegate.browserActivated(hierarchyBrowser);
	}

	private boolean isIdentifiableStatement(PsiElement element)
	{
		if(element instanceof PomTargetPsiElement)
		{
			PomTarget target = ((PomTargetPsiElement) element).getTarget();
			if(target instanceof DomTarget)
			{
				DomElement domElement = ((DomTarget) target).getDomElement();
				if(domElement instanceof SqlMapIdentifiableStatement)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return XMLLanguage.INSTANCE;
	}
}
