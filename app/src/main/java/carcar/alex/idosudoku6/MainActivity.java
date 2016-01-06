package carcar.alex.idosudoku6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewGame(View view) {
    }

    public void onCellTouch(View a) {
        if(selected==-1) return;
        Button currentCell = (Button) a;
		currentCell.setText(R.string.number1);
    }

    public void onChoice(View a) {
        if (selected != -1) {
            int lastId = 0;
            switch(selected) {
                case 1:
                    lastId = R.id.choose1;
                    break;
                case 2:
                    lastId = R.id.choose2;
                    break;
                case 3:
                    lastId = R.id.choose3;
                    break;
                case 4:
                    lastId = R.id.choose4;
                    break;
                case 5:
                    lastId = R.id.choose5;
                    break;
                case 6:
                    lastId = R.id.choose6;
                    break;
                case 0:
                    lastId = R.id.choose_delete;
                    break;
            }
            Button lastButton = (Button) findViewById(lastId);
            lastButton.setBackgroundResource(R.color.off_color);
        }
        if (a.getId() == R.id.choose1) selected = 1;
        if (a.getId() == R.id.choose2) selected = 2;
        if (a.getId() == R.id.choose3) selected = 3;
        if (a.getId() == R.id.choose4) selected = 4;
        if (a.getId() == R.id.choose5) selected = 5;
        if (a.getId() == R.id.choose6) selected = 6;
        if (a.getId() == R.id.choose_delete) selected = 0;
        a.setBackgroundResource(R.color.on_color);
    }
}
