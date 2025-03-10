package ca.sunlis.greenhouses.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ca.sunlis.greenhouses.Main;
import ca.sunlis.greenhouses.blocks.tileentity.TEHotbed;
import ca.sunlis.greenhouses.init.ModBlocks;
import ca.sunlis.greenhouses.init.ModItems;
import ca.sunlis.greenhouses.util.IHasModel;
import ca.sunlis.greenhouses.util.Reference;

import javax.annotation.Nullable;

public class BlockHotbed extends BlockContainer implements IHasModel {

  public BlockHotbed(String name, Material material) {
    super(Material.WOOD);
    setTranslationKey(name);
    setRegistryName(name);
    setCreativeTab(Main.creativeTab);
    setHardness(2F);
    ModBlocks.BLOCKS.add(this);
    ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing playerFacing, float hitX, float hitY, float hitZ) {

    if(!worldIn.isRemote){
      player.openGui(
        Main.INSTANCE, Reference.GUI_HOT_BED, worldIn,
        pos.getX(), pos.getY(), pos.getZ());
    }
    return true;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TEIceBunker tile = (TEIceBunker)worldIn.getTileEntity(pos);
    InventoryHelper.dropInventoryItems(worldIn, pos, tile);
    super.breakBlock(worldIn, pos, state);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    if(stack.hasDisplayName()){
      TileEntity entity = worldIn.getTileEntity(pos);
      if(entity instanceof TEIceBunker){
        //((TECellarShelf)entity).setCustomName(stack.getDisplayName());
      }
    }

  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(World world, int i) {
    return new TEIceBunker();
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public void registerModels() {
    Main.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
  }
}
