package consulo.mybatis.module.extension;

import consulo.module.content.layer.ModuleRootLayer;
import consulo.module.content.layer.extension.ModuleExtensionBase;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisModuleExtension extends ModuleExtensionBase<MyBatisModuleExtension>
{
	public MyBatisModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}
}
