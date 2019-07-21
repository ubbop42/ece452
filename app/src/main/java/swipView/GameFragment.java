package swipView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.thumbtrainer.R;


public class GameFragment extends Fragment {

    private OnGameOver gameOverListener;

    public interface OnGameOver {
	void onGameOver(int score);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.fragment_ninja, null);

	GameSurfaceView gameView = v.findViewById(R.id.gameview);
	gameView.setGameOverListener(gameOverListener);
	
	return v;
    }

}