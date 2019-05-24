package com.slb.ttdandroidframework.ui.activity;

import android.bluetooth.BluetoothSocket;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.pires.obd.exceptions.MisunderstoodCommandException;
import com.github.pires.obd.exceptions.NoDataException;
import com.github.pires.obd.exceptions.UnableToConnectException;
import com.hwangjr.rxbus.RxBus;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.ttdandroidframework.Base;
import com.slb.ttdandroidframework.R;
import com.slb.ttdandroidframework.command.mode2.Service2Command;
import com.slb.ttdandroidframework.command.mode6.Mode6AvailablePidsCommand_01_20;
import com.slb.ttdandroidframework.command.mode6.Mode6AvailablePidsCommand_21_40;
import com.slb.ttdandroidframework.command.mode6.Mode6AvailablePidsCommand_41_60;
import com.slb.ttdandroidframework.command.mode6.Service6Command;
import com.slb.ttdandroidframework.event.ObdServiceStateEvent;
import com.slb.ttdandroidframework.http.bean.FreezeFrameEntity;
import com.slb.ttdandroidframework.http.bean.FreezeFrameInsideEntity;
import com.slb.ttdandroidframework.http.bean.ModeSixEntity;
import com.slb.ttdandroidframework.http.callback.ActivityDialogCallback;
import com.slb.ttdandroidframework.http.dns.DnsFactory;
import com.slb.ttdandroidframework.http.model.LzyResponse;
import com.slb.ttdandroidframework.ui.adapter.FreezeFrameAdapter;
import com.slb.ttdandroidframework.ui.adapter.ModeSixAdapter;
import com.slb.ttdandroidframework.util.BluetoothUtil;
import com.slb.ttdandroidframework.util.config.BizcContant;
import com.slb.ttdandroidframework.util.config.Mode2Util;
import com.slb.ttdandroidframework.util.config.ObdConfig;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.slb.ttdandroidframework.ui.activity.ReadErrorCodeActivity.DATA_OK_CODE;
import static com.slb.ttdandroidframework.util.config.ObdConfig.OBD_COMMAND_FAILURE_IE;
import static com.slb.ttdandroidframework.util.config.ObdConfig.OBD_COMMAND_FAILURE_IO;
import static com.slb.ttdandroidframework.util.config.ObdConfig.OBD_COMMAND_FAILURE_MIS;
import static com.slb.ttdandroidframework.util.config.ObdConfig.OBD_COMMAND_FAILURE_NODATA;
import static com.slb.ttdandroidframework.util.config.ObdConfig.OBD_COMMAND_FAILURE_UTC;

/**
 * 作者：Juan on 2017/7/30 18:33
 * 邮箱：154336119@qq.com
 * 描述：mode6
 */
public class Mode6Activity extends BaseActivity {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private ModeSixAdapter mAdapter;
    private BluetoothSocket sock;
    private ModeSixTask mModeSixTask;
    private List<ModeSixEntity> mList = new ArrayList<>();
    private Handler mHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "Message received on handler");
            switch (msg.what) {
                case ObdConfig.NO_BLUETOOTH_DEVICE_SELECTED:
                    showToastMsg(getString(R.string.text_bluetooth_nodevice));
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_bluetooth_nodevice));
                    break;
                case ObdConfig.CANNOT_CONNECT_TO_DEVICE:
                    showToastMsg(getString(R.string.text_bluetooth_error_connecting));
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_bluetooth_error_connecting));
                    break;
                case ObdConfig.OBD_COMMAND_FAILURE:
                    showToastMsg(getString(R.string.text_obd_command_failure));
                    Logger.d("模式6：troubleCodesCommand异常:------"+getString(R.string.text_obd_command_failure));
                    break;
                case OBD_COMMAND_FAILURE_IO:
                    showToastMsg(BizcContant.STR_OBD_DISCONNECTED);
                    BluetoothUtil.closeSocket();
                    Logger.d("模式6：设备已断开连接，请重新连接");
                    finish();
                    break;
                case OBD_COMMAND_FAILURE_IE:
                    showToastMsg(getString(R.string.text_obd_command_failure) + " IE");
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_obd_command_failure));
                    break;
                case OBD_COMMAND_FAILURE_MIS:
                    showToastMsg(getString(R.string.text_obd_command_failure) + " MIS");
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_obd_command_failure));
                    break;
                case OBD_COMMAND_FAILURE_UTC:
//                    showToastMsg(getString(R.string.text_obd_command_failure) + " UTC");
                    showToastMsg(BizcContant.STR_OBD_DISCONNECTED);
                    BluetoothUtil.closeSocket();
                    Logger.d("模式6：设备已断开连接，请重新连接");
                    finish();
                    break;
                case OBD_COMMAND_FAILURE_NODATA:
                    showToastMsg(getString(R.string.text_noerrors));
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_noerrors));
                    break;
                case ObdConfig.NO_DATA:
                    showToastMsg(getString(R.string.text_dtc_no_data));
                    Logger.d("模式6：troubleCodesCommand异常:-------"+getString(R.string.text_dtc_no_data));
                    ///finish();
                    break;
                case DATA_OK_CODE:
//                    dataOk((String) msg.obj);
                    Logger.d("模式6：troubleCodesCommand:DATA_OK_CODE------");

                    mList = (List<ModeSixEntity>)msg.obj;
                    mAdapter.setNewData(mList);
                    break;
            }
            return false;
        }
    });


    @Override
    protected String setToolbarTitle() {
        return getString(R.string.mode_6);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mode_six;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mAdapter = new ModeSixAdapter(mList ,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.parseColor("#2B3139"))
                        .sizeResId(R.dimen.distance_5)
                        .build());
//        //测试
//        for (int i = 0; i < 10; i++) {
//            FreezeFrameEntity freezeFrameEntity = new FreezeFrameEntity();
//            mList.add(freezeFrameEntity);
//        }
//        mAdapter.addData(mList);
        try {
            sock = BluetoothUtil.getSockInstance();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.d("模式6：sock连接异常:-------"+e.getMessage());
            showToastMsg("Bluetooth connect failed");
            return;
        }
        executeMode6Command();
    }

    /**
     * 请求mode6
     */
    public void executeMode6Command() {
        try {
            if (BluetoothUtil.getDeviceInstance() == null) {
                mHandler.obtainMessage(ObdConfig.NO_BLUETOOTH_DEVICE_SELECTED).sendToTarget();
            } else {
                mModeSixTask = new ModeSixTask();
                mModeSixTask.execute();
                Logger.d("模式6：mModeSixTask.execute()");
            }
        } catch (IOException e) {
            mHandler.obtainMessage(ObdConfig.NO_BLUETOOTH_DEVICE_SELECTED).sendToTarget();
            e.printStackTrace();
            Logger.d("模式6：异常--------NO_BLUETOOTH_DEVICE_SELECTED");
        }
    }
    //    @Override
//    protected Observable<HttpResult<Object, CookBillEntity>> requestHttp() {
//        return RetrofitSerciveFactory.provideComService().getCookBillList(1);
//    }
    private class ModeSixTask extends AsyncTask<String, Integer, List<ModeSixEntity>> {
        @Override
        protected void onPreExecute() {
            //Create a new progress dialog
            showWaitDialog("loading...");
            Logger.d("模式6：ModeSixTask.onPreExecute");
        }

        @Override
        protected List<ModeSixEntity> doInBackground(String... params) {
            List<ModeSixEntity> list = new ArrayList<>();
            //Get the current thread's token
            synchronized (this) {
                try {
                    //Step2:查询mode6可用的tid
//                    Logger.d("模式6：=======查询mode6可用的tid");
//                    List<String> tidList = new ArrayList<>();
//                    try {
//                        Mode6AvailablePidsCommand_01_20 available1 = new Mode6AvailablePidsCommand_01_20();
//                        available1.run(sock.getInputStream(),sock.getOutputStream());
//                        String result1 = available1.getFormattedResult();
//                        tidList.addAll(Arrays.asList(result1.split(",")));
//                    } catch (Exception e) {
//                        Logger.d("模式6：Mode6AvailablePidsCommand_01_20===========异常:-------"+e.getMessage());
//                    }
//
//                    try {
//                        Mode6AvailablePidsCommand_21_40 available2 = new Mode6AvailablePidsCommand_21_40();
//                        available2.run(sock.getInputStream(),sock.getOutputStream());
//                        String result2 = available2.getFormattedResult();
//                        tidList.addAll(Arrays.asList(result2.split(",")));
//                    } catch (Exception e) {
//                        Logger.d("模式6：Mode6AvailablePidsCommand_21_40===========异常:-------"+e.getMessage());
//                    }
//
//                    try {
//                        Mode6AvailablePidsCommand_41_60 available3 = new Mode6AvailablePidsCommand_41_60();
//                        available3.run(sock.getInputStream(),sock.getOutputStream());
//                        String result3 = available3.getFormattedResult();
//                        tidList.addAll(Arrays.asList(result3.split(",")));
//                    } catch (Exception e) {
//                        Logger.d("模式6：Mode6AvailablePidsCommand_41_60===========异常:-------"+e.getMessage());
//                    }
//
//                    System.out.println("要查询的mode6 TID集合:"+tidList);
//                    Logger.d("模式6：=======要查询的mode6 TID集合:"+tidList);
//                    //Step2

                    //Step3:执行mode6查询命令并解析结果
                    String commandString;
                    Logger.d("模式6：=======执行mode6查询命令并解析结果");
                    commandString = "06 21";
                    Logger.d("模式6：=======commandString:"+commandString);
                    Service6Command command = new Service6Command(commandString);
                    command.run(sock.getInputStream(),sock.getOutputStream());
//                    for (String tid : tidList) {
//                        commandString = "06 "+tid;
//                        Logger.d("模式6：=======commandString:"+commandString);
//                        Service6Command command = new Service6Command(commandString);
//                        command.run(sock.getInputStream(),sock.getOutputStream());
//                        if(!TextUtils.isEmpty(command.getFormattedResult())){
//                            list.addAll(command.getList());
//                            for(ModeSixEntity modeSixEntity : command.getList()){
//                                Logger.d("模式6：=======modeSixEntity:"+ modeSixEntity.toString());
//                            }
//                        }else{
//                            Logger.d("模式6：======没新的数据:");
//                        }
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("DTCERR", e.getMessage());
                    mHandler.obtainMessage(OBD_COMMAND_FAILURE_IO).sendToTarget();
                    return null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("DTCERR", e.getMessage());
                    mHandler.obtainMessage(OBD_COMMAND_FAILURE_IE).sendToTarget();
                    return null;
                } catch (UnableToConnectException e) {
                    e.printStackTrace();
                    Log.e("DTCERR", e.getMessage());
                    mHandler.obtainMessage(OBD_COMMAND_FAILURE_UTC).sendToTarget();
                    return null;
                } catch (MisunderstoodCommandException e) {
                    e.printStackTrace();
                    Log.e("DTCERR", e.getMessage());
                    mHandler.obtainMessage(OBD_COMMAND_FAILURE_MIS).sendToTarget();
                    return null;
                } catch (NoDataException e) {
                    Log.e("DTCERR", e.getMessage());
//                    mHandler.obtainMessage(OBD_COMMAND_FAILURE_NODATA).sendToTarget();
                    mHandler.obtainMessage(DATA_OK_CODE, list).sendToTarget();
                } catch (Exception e) {
                    Log.e("DTCERR", e.getMessage());
                    mHandler.obtainMessage(ObdConfig.OBD_COMMAND_FAILURE).sendToTarget();
                } finally {
                    hideWaitDialog();
                }

            }

            return list;
        }

        public void closeSocket(BluetoothSocket sock) {
            if (sock != null)
                // close socket
                try {
                    sock.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<ModeSixEntity> result) {
            hideWaitDialog();
            mHandler.obtainMessage(DATA_OK_CODE, result).sendToTarget();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().post(new ObdServiceStateEvent(true));
    }

}
