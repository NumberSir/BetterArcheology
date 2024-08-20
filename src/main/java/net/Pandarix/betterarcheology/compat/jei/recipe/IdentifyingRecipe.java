package net.Pandarix.betterarcheology.compat.jei.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.Pandarix.betterarcheology.BetterArcheology;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class IdentifyingRecipe implements Recipe<SimpleInventory>
{
    private final Ingredient input;
    private final ItemStack result;
    private final Identifier id;
    private static final int POSSIBLE_RESULT_COUNT = Registries.ENCHANTMENT.streamEntries().filter(reference -> reference.registryKey().getValue().getNamespace().equals(BetterArcheology.MOD_ID)).toList().size();

    public IdentifyingRecipe(Ingredient inputItems, ItemStack result, Identifier id)
    {
        this.input = inputItems;
        this.result = result;
        this.id = id;
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
        return this.getOutput(pRegistryAccess);
    }

    @Override
    public boolean fits(int width, int height)
    {
        return true;
    }

    @NotNull
    @Override
    public ItemStack getOutput(DynamicRegistryManager pRegistryAccess)
    {
        return this.getResult();
    }

    /**
     * Extra method instead of {@link #getOutput} for use without unnecessary parameter
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
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(PacketByteBuf packetByteBuf, IdentifyingRecipe recipe)
        {
            recipe.getIngredients().get(0).write(packetByteBuf);
            packetByteBuf.writeItemStack(recipe.result);
        }

        @Override
        public IdentifyingRecipe read(Identifier id, JsonObject json)
        {
            JsonObject itemStackObject = JsonHelper.getObject(json, "result");

            ItemStack output = ShapedRecipe.outputFromJson(itemStackObject);
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "input"));

            // custom enchantment nbt parsing because fabrics outputFromJson refuses to
            if (itemStackObject.has("nbt"))
            {
                try
                {
                    JsonElement element = itemStackObject.get("nbt");
                    NbtCompound nbt = StringNbtReader.parse(JsonHelper.toSortedString(element));
                    output.setNbt(nbt);
                } catch (Exception e)
                {
                    BetterArcheology.LOGGER.error("Could not parse Enchantment nbt compound for IdentifyingRecipe " + id.toString() + ":" + e);
                }
            }

            return new IdentifyingRecipe(input, output, id);
        }

        @Override
        public IdentifyingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
        {
            Ingredient input = Ingredient.fromPacket(packetByteBuf);
            ItemStack result = packetByteBuf.readItemStack();

            return new IdentifyingRecipe(input, result, identifier);
        }
    }

    @Override
    public Identifier getId()
    {
        return id;
    }
}