package net.ishchenko.idea.minibatis.inspections.mapper;

import com.intellij.java.language.psi.PsiAnnotation;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiIdentifier;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.component.ExtensionImpl;
import consulo.ide.ServiceManager;
import consulo.language.editor.inspection.LocalQuickFix;
import consulo.language.editor.inspection.ProblemDescriptor;
import consulo.language.editor.inspection.ProblemHighlightType;
import consulo.language.editor.inspection.scheme.InspectionManager;
import consulo.localize.LocalizeValue;
import jakarta.annotation.Nonnull;
import net.ishchenko.idea.minibatis.DomFileElementsFinder;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.06.12
 * Time: 11:39
 */
@ExtensionImpl
public class NoMapperStatementInspection extends MyBatisInspection {
    @Override
    public ProblemDescriptor[] checkMethod(@Nonnull PsiMethod method, @Nonnull InspectionManager manager, boolean isOnTheFly, Object state) {
        PsiClass containingClass = method.getContainingClass();
        if (containingClass != null && containingClass.isInterface()) {

            PsiIdentifier methodName = method.getNameIdentifier();
            DomFileElementsFinder finder = ServiceManager.getService(containingClass.getProject(), DomFileElementsFinder.class);
            boolean existsMapperStatement = finder.existsMapperStatement(method);

            boolean containsIbatisAnnotation = false;
            for (PsiAnnotation annotation : method.getModifierList().getAnnotations()) {
                String annotationName = annotation.getQualifiedName();
                if (annotationName != null && annotationName.startsWith("org.apache.ibatis.annotations")) {
                    containsIbatisAnnotation = true;
                    break;
                }
            }

            if (!existsMapperStatement && !containsIbatisAnnotation && methodName != null) {
                return new ProblemDescriptor[]{
                    manager.createProblemDescriptor(methodName, "Statement with id=\"#ref\" not defined in mapper xml", (LocalQuickFix) null, ProblemHighlightType.GENERIC_ERROR, isOnTheFly)
                };
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public LocalizeValue getDisplayName() {
        return LocalizeValue.localizeTODO("Mapper interface method not defined in xml");
    }

}
