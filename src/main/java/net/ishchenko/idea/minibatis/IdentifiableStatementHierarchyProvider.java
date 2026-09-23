package net.ishchenko.idea.minibatis;

import com.intellij.java.impl.ide.hierarchy.call.JavaCallHierarchyModel;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.dataContext.DataContext;
import consulo.language.Language;
import consulo.language.editor.hierarchy.HierarchyKind;
import consulo.language.editor.hierarchy.HierarchyModel;
import consulo.language.editor.hierarchy.HierarchyProvider;
import consulo.language.editor.hierarchy.StandardHierarchyKinds;
import consulo.language.pom.PomTarget;
import consulo.language.pom.PomTargetPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReference;
import consulo.language.psi.search.ReferencesSearch;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.project.Project;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.xml.dom.DomElement;
import consulo.xml.dom.DomTarget;
import consulo.xml.language.XMLLanguage;
import net.ishchenko.idea.minibatis.model.sqlmap.SqlMapIdentifiableStatement;
import org.jspecify.annotations.Nullable;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Max
 * Date: 02.05.13
 * Time: 21:40
 */
@ExtensionImpl
public class IdentifiableStatementHierarchyProvider implements HierarchyProvider<PsiMethod> {
    @Override
    public HierarchyKind getKind() {
        return StandardHierarchyKinds.CALL;
    }

    @Nullable
    @Override
    @RequiredReadAction
    public PsiMethod getTarget(DataContext dataContext) {
        PsiElement psiElement = dataContext.getData(PsiElement.KEY);
        if (!isIdentifiableStatement(psiElement)) {
            return null;
        }

        Collection<PsiReference> refs = ReferencesSearch.search(psiElement).findAll();
        if (refs.size() != 1) {
            return null;
        }

        PsiElement element = refs.iterator().next().getElement();
        return PsiTreeUtil.getParentOfType(element, PsiMethod.class, true);
    }

    @RequiredReadAction
    @Override
    public HierarchyModel<PsiMethod> createModel(Project project, PsiMethod target) {
        return new JavaCallHierarchyModel(project, target);
    }

    @RequiredUIAccess
    @Override
    public void targetSelected(PsiMethod target) {
        if (target.canNavigate()) {
            target.navigate(false);
        }
    }

    @RequiredReadAction
    private static boolean isIdentifiableStatement(@Nullable PsiElement element) {
        if (element instanceof PomTargetPsiElement pomTargetPsiElement) {
            PomTarget target = pomTargetPsiElement.getTarget();
            if (target instanceof DomTarget domTarget) {
                DomElement domElement = domTarget.getDomElement();
                return domElement instanceof SqlMapIdentifiableStatement;
            }
        }
        return false;
    }

    @Override
    public Language getLanguage() {
        return XMLLanguage.INSTANCE;
    }
}
