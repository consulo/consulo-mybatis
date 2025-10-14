package net.ishchenko.idea.minibatis.inspections.mapper;

import com.intellij.java.analysis.impl.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.java.language.JavaLanguage;
import consulo.language.Language;
import consulo.localize.LocalizeValue;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Created by IntelliJ IDEA.
 * User: Max
 * Date: 02.06.12
 * Time: 11:47
 */
public abstract class MyBatisInspection extends BaseJavaLocalInspectionTool {
    @Nullable
    @Override
    public Language getLanguage() {
        return JavaLanguage.INSTANCE;
    }

    @Nonnull
    @Override
    public LocalizeValue getGroupDisplayName() {
        return LocalizeValue.localizeTODO("iBATIS/MyBatis issues");
    }

    @Nonnull
    @Override
    public String getShortName() {
        return this.getClass().getSimpleName();
    }
}
