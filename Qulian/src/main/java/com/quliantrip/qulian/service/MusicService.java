package com.quliantrip.qulian.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.quliantrip.qulian.ui.fragment.findFragment.SpotDetailFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {

    private MediaPlayer player;
    private Timer timer;
    private TimerTask task;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //当服务第一次开启的时候回执行这个方法
    @Override
    public void onCreate() {
        //(1)创建meidaplayer 实例
        player = new MediaPlayer();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 播放音乐的方法
    public void playMusic() {
        try {
            if (player == null)
                player = new MediaPlayer();
            player.reset();
            player.setDataSource("http://192.168.0.192:8080/xpg.mp3");
            player.prepareAsync();
            player.setOnPreparedListener(new OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    //(4)开始播放
                    player.start();
                    updateSeekBar();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 播放音乐的方法,改变路径
    public void playMusic(String url) {
        try {
            if (player == null)
                player = new MediaPlayer();
            player.reset();
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    //(4)开始播放
                    player.start();
                    updateSeekBar();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新进度条的逻辑
    private void updateSeekBar() {
        //[1]获取歌曲总时长 和 当前播放的进度
        final int duration = player.getDuration();
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                //每隔一秒钟获取一次 当前歌曲的位置
                int currentPosition = player.getCurrentPosition();
                //创建message对象
                Message msg = Message.obtain();
                //创建bundle 对象 实际底层就是map
                Bundle bundle = new Bundle();
                bundle.putInt("duration", duration);
                bundle.putInt("currentPosition", currentPosition);
                msg.setData(bundle);

                //发送消息
                SpotDetailFragment.handler.sendMessage(msg);
            }
        };
        timer.schedule(task, 50, 1000); //50毫秒后 每隔一秒钟执行一次 task
        //[3]当歌曲播放完成的时候  把timer 和 tast 取消
        player.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                System.out.println("歌曲播放完了");
                timer.cancel(); //取消任务
                task.cancel();
            }
        });
    }

    // 暂停音乐的方法
    public void pauseMusic() {
        player.pause(); //音乐暂停了
    }

    // 继续播放音乐的方法
    public void replayMusic() {
        player.start();
    }

    //设置音乐播放指定位置的方法
    public void setSeekPosition(int position) {
        player.seekTo(position);
    }

    //(1)定义中间人对象
    private class MyBinder extends Binder implements Iservice {

        @Override
        public void callPlayMusic() {
            //调用播放音乐的方法
            playMusic();
        }

        @Override
        public void callPauseMusic() {
            //调用暂停音乐的方法
            pauseMusic();
        }

        @Override
        public void callRePlayMusic() {
            //调用继续播放的方法
            replayMusic();
        }

        @Override
        public void callSetSeekPosition(int position) {
            setSeekPosition(position);
        }

        @Override
        public void callPlayMusic(String url) {
            playMusic(url);
        }
    }
}
