/**
 * @author VISTALL
 * @since 22/01/2023
 */
open module consulo.mybatis {
    requires consulo.application.api;
    requires consulo.code.editor.api;
    requires consulo.datacontext.api;
    requires consulo.disposer.api;
    requires consulo.ide.api;
    requires consulo.language.api;
    requires consulo.language.editor.api;
    requires consulo.language.impl;
    requires consulo.localize.api;
    requires consulo.module.api;
    requires consulo.module.content.api;
    requires consulo.navigation.api;
    requires consulo.project.api;
    requires consulo.ui.api;
    requires consulo.util.lang;

    requires com.intellij.xml.api;
    requires com.intellij.xml.dom.api;
    requires consulo.java;

    // TODO remove
    requires java.desktop;
}
