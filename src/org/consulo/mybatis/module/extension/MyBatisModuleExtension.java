package org.consulo.mybatis.module.extension;

import org.consulo.module.extension.impl.ModuleExtensionImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.roots.ModifiableRootModel;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisModuleExtension extends ModuleExtensionImpl<MyBatisModuleExtension>
{
	public MyBatisModuleExtension(@NotNull String id, @NotNull ModifiableRootModel rootModel)
	{
		super(id, rootModel);
	}
}
