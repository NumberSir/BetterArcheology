package net.Pandarix.betterarcheology.compat.jei.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.Pandarix.betterarcheology.BetterArcheology;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class IdentifyingRecipe implements Recipe<SimpleInventory>
{
    private final Ingredient input;
    private final ItemStack result;
    private static final int POSSIBLE_RESULT_COUNT = Registries.ENCHANTMENT.streamEntries().filter(reference -> reference.registryKey().getValue().getNamespace().equals(BetterArcheology.MOD_ID)).toList().size();

    public IdentifyingRecipe(Ingredient inputItems, ItemStack result)
    {
        this.input = inputItems;
        this.result = result;
    }

    @Override
    public boolean matches(@NotNull SimpleInventory pContainer, World pLevel)
    {
        if (pLevel.isClient())
        {
            return false;
        }

        return input.test(pContainer.getStack(0));
    }

    @Override
    public boolean isIgnoredInRecipeBook()
    {
        return true;
    }

    @Override
    @NotNull
    public DefaultedList<Ingredient> getIngredients()
    {
        return DefaultedList.copyOf(Ingredient.EMPTY, input);
    }

    @NotNull
    @Override
    public ItemStack craft(SimpleInventory pContainer, DynamicRegistryManager pRegistryAccess)
    {
        return this.getResult(pRegistryAccess);
    }

    @Override
    public boolean fits(int width, int height)
    {
        return true;
    }

    @NotNull
    @Override
    public ItemStack getResult(DynamicRegistryManager pRegistryAccess)
    {
        return this.getResult();
    }

    /**
     * Extra method instead of {@link #getResult} for use without unnecessary parameter
     *
     * @return ItemStack to be crafted when done
     */
    public ItemStack getResult()
    {
        //Adding the Enchantment Tags
        ItemStack modifiedResultBook = result.copy();

        //Adding the Custom Name Tags
        NbtCompound nameModification = new NbtCompound();
        nameModification.putString("Name", "{\"translate\":\"item.betterarcheology.identified_artifact\"}");

        //Adding Chance as Lore Tag
        NbtList lore = new NbtList();
        lore.add(NbtString.of(String.format("{\"text\":\"Chance: 1/%d\",\"color\":\"aqua\"}", POSSIBLE_RESULT_COUNT)));
        nameModification.put("Lore", lore);

        //output the book with the modifications
        modifiedResultBook.setSubNbt("display", nameModification);
        return modifiedResultBook;
    }

    @Override
    @NotNull
    public RecipeSerializer<?> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    @Override
    @NotNull
    public RecipeType<?> getType()
    {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<IdentifyingRecipe>
    {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<IdentifyingRecipe>
    {
        private static final Codec<IdentifyingRecipe> CODEC = RecordCodecBuilder.create(
                (builder) -> builder.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input").forGetter((IdentifyingRecipe recipe) -> recipe.input),
                        ItemStack.CODEC.fieldOf("result").forGetter((IdentifyingRecipe recipe) -> recipe.result)
                ).apply(builder, IdentifyingRecipe::new));

        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull Codec<IdentifyingRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, IdentifyingRecipe recipe)
        {
            recipe.getIngredients().get(0).write(packetByteBuf);
            packetByteBuf.writeItemStack(recipe.result);
        }

        @Override
        public IdentifyingRecipe read(PacketByteBuf packetByteBuf)
        {
            Ingredient input = Ingredient.fromPacket(packetByteBuf);
            ItemStack result = packetByteBuf.readItemStack();

            return new IdentifyingRecipe(input, result);
        }
    }
}