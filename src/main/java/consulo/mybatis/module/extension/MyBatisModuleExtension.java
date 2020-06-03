package consulo.mybatis.module.extension;

import javax.annotation.Nonnull;
import consulo.module.extension.impl.ModuleExtensionImpl;
import consulo.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 19:52/27.06.13
 */
public class MyBatisModuleExtension extends ModuleExtensionImpl<MyBatisModuleExtension>
{
	public MyBatisModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}
}
