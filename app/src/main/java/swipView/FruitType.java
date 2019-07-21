package swipView;

import com.example.thumbtrainer.R;

import java.util.Random;

public enum FruitType {
    WATERMELON(R.drawable.dog), STRAWBERRY(R.drawable.chicken),
    PINEAPPLE(R.drawable.fish);

    private final int resourceId;

    private FruitType(int resourceId) {
	this.resourceId = resourceId;
    }

    public int getResourceId() {
	return resourceId;
    }

    private static final Random random = new Random();

    public static FruitType randomFruit() {
	return FruitType.values()[random.nextInt(FruitType.values().length)];
    }
}