package net.ishchenko.idea.minibatis;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiMethod;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.ReadAction;
import consulo.application.util.function.Processor;
import consulo.ide.ServiceManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.search.DefinitionsScopedSearch;
import consulo.language.psi.search.DefinitionsScopedSearchExecutor;
import consulo.xml.util.xml.DomElement;
import jakarta.annotation.Nonnull;

import java.util.function.Predicate;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 04.01.12
 * Time: 22:16
 */
@ExtensionImpl
public class MyBatis3ProxiesDefinitionsSearcher implements DefinitionsScopedSearchExecutor {
    @Override
    public boolean execute(@Nonnull DefinitionsScopedSearch.SearchParameters searchParameters, @Nonnull Predicate<? super PsiElement> consumer) {
        ReadAction.run(() ->
        {
            PsiElement element = searchParameters.getElement();

            DomFileElementsFinder finder = element.getProject().getInstance(DomFileElementsFinder.class);
            Processor<DomElement> processor = domElement -> consumer.test(domElement.getXmlElement());

            if (element instanceof PsiClass) {
                finder.processMappers((PsiClass) element, processor);
            }
            else if (element instanceof PsiMethod) {
                finder.processMapperStatements((PsiMethod) element, processor);
            }
        });
        return true;
    }
}
