package com.example.administrator.mycalculator2;

        import android.content.DialogInterface;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText weightEditText;
    private Button heightButton;
    private RadioButton man;
    private RadioButton woman;
    private TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取UI控件的实例
        weightEditText= (EditText) findViewById(R.id.editText);
        heightButton= (Button) findViewById(R.id.button);
        man= (RadioButton)findViewById(R.id.man);
        woman= (RadioButton) findViewById(R.id.woman);
        textView4= (TextView) findViewById(R.id.textView4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEven();
    }
    private void registerEven(){
        heightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否输入体重
                if(!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否选择性别
                    if (man.isChecked()||woman.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        if (man.isChecked()) {
                            sb.append("男性标准身高：");
                            //计算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "厘米");
                        } else {
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int)result + "厘米");
                        }
                        //显示计算结果
                        textView4.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }
                }
                    else{
                        showMessage("请输入体重！");
                    }
            }
        });
    }

    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }
    //计算标准身高
    private double evaluateHeight(double weight,String sex){
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    //菜单事件处理

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
