package com.snh48g.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int id = 0;
    Integer[] imglist = {R.drawable.snh48, R.drawable.bej48, R.drawable.gnz48, R.drawable.ckg48};
    Button game_2048, synthetic, about_help, exchange_background, exit;
    static final int TIME_EXIT=2000;
    long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game_2048 = findViewById(R.id.game_2048);
        synthetic = findViewById(R.id.synthetic);
        about_help = findViewById(R.id.about_help);
        exchange_background = findViewById(R.id.exchange_background);
        exit = findViewById(R.id.exit);
        game_2048.setTag(1);
        synthetic.setTag(2);
        about_help.setTag(3);
        exchange_background.setTag(4);
        exit.setTag(5);
        game_2048.setOnClickListener(this);
        synthetic.setOnClickListener(this);
        about_help.setOnClickListener(this);
        exchange_background.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        switch (tag){
            case 1:
                Intent intent1 = new Intent(MainActivity.this, Game_2048Activity.class);
                startActivity(intent1);
                break;
            case 2:
                /*Intent intent2 = new Intent(MainActivity.this, SyntheticActivity.class);
                startActivity(intent2);
                break;*/
                Toast.makeText(this,"这款游戏正在开发ing，敬请期待！可以先玩玩别的游戏哦",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                AlertDialog alert = new AlertDialog.Builder(this).create();
                alert.setTitle("关于/帮助");
                alert.setMessage("关于："+"\n"+"软件名称：48小游戏_beta先行测试版"+"\n"+
                        "软件版本：1.0.beta.20210212"+"\n"+"开发者：Forever_LJW"+"\n"+
                        "\n"+"帮助："+"\n"+"1.app内集成了两款游戏："+
                        "一款是小偶像版2048，由2048改版而来，玩法和2048一致；"+
                        "另一款是合成小偶像，由合成大西瓜改版而来，玩法和合成大西瓜一致"+"\n"+
                        "2.游戏具体规则会在各游戏界面展示，点击相应游戏按钮即可玩耍"+"\n"+
                        "3.目前只可以玩小偶像版2048，合成小偶像正在加紧开发ing，敬请期待"+"\n"+
                        "4.此版本为先行测试版，小偶像版2048只有部分小偶像和彩蛋，其余内容将在正式版全部展现，敬请期待");
                alert.show();
                break;
            case 4:
                id++;
                if(id >= imglist.length){
                    id = 0;
                }
                findViewById(R.id.main).setBackgroundResource(imglist[id]);
                break;
            case 5:
                if(mBackPressed+TIME_EXIT > System.currentTimeMillis()){
                    super.onBackPressed();
                    return;
                }else{
                    Toast.makeText(this,"再点击一次退出程序",Toast.LENGTH_SHORT).show();
                    mBackPressed = System.currentTimeMillis();
                }
                break;
        }
    }
}