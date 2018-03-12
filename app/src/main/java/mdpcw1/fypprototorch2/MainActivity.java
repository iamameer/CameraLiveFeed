package mdpcw1.fypprototorch2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    //global
    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView;
    private SurfaceHolder camHolder;
    private boolean previewRunning;
    final Context context = this;
    public static Camera camera = null;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurView = (SurfaceView) findViewById(R.id.surfaceView);
        camHolder = SurView.getHolder();
        camHolder.addCallback(this);
        camHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        Log.d("p","onCreate");
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try{
            camera=Camera.open();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            finish();
        }

        Log.d("p","surface created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(previewRunning){
            camera.stopPreview();
        }

        Camera.Parameters camParams = camera.getParameters();
        Camera.Size size = camParams.getSupportedPreviewSizes().get(0);
        camParams.setPreviewSize(size.width, size.height);
        camera.setParameters(camParams);
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            previewRunning=true;
        }catch(Exception e){
            e.printStackTrace();
        }

        Log.d("p","surface changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera=null;
        Log.d("p","surface destroyed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("p","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("p","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("p","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("p","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("p","onRestart");
    }
}
