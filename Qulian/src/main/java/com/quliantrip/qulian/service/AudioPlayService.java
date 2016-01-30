//package com.quliantrip.qulian.service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Random;
//
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnCompletionListener;
//import android.media.MediaPlayer.OnPreparedListener;
//import android.os.Binder;
//import android.os.Build.VERSION;
//import android.os.IBinder;
//import android.support.v4.app.NotificationCompat;
//import android.widget.RemoteViews;
//
//import com.heima57.mobileplayer.R;
//import com.heima57.mobileplayer.bean.AudioItem;
//import com.heima57.mobileplayer.ui.activity.AudioPlayerActivity;
//import com.heima57.mobileplayer.util.StringUtil;
//
//public class AudioPlayService extends Service{
//	public static final String ACTION_PREPARED = "ACTION_PREPARED";//准备完成的action
//	public static final String ACTION_COMPLETION = "ACTION_COMPLETION";//播放完成的action
//
//	private static final int VIEW_PRE = 1;//标识通知中的上一个按钮
//	private static final int VIEW_NEXT = 2;//标识通知中的下一个按钮
//	private static final int VIEW_CONTAINER = 3;//标识通知中的根布局
//
//	public static final int MODE_RANDOM = 1;//随机播放
//	public static final int MODE_SINGLE_REPEAT = 2;//单曲循环
//	public static final int MODE_ALL_REPEAT = 3;//循环播放，全部循环
//	private int playMode = MODE_RANDOM;//默认模式是随机播放
//
//	private MediaPlayer mediaPlayer;
//	private AudioPlayBinder audioPlayBinder;
//	private int currentPosition;
//	private AudioItem audioItem;
//	private ArrayList<AudioItem> audioList;
//
//	private SharedPreferences sp;
//	@Override
//	public IBinder onBind(Intent intent) {
//		return audioPlayBinder;
//	}
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		audioPlayBinder = new AudioPlayBinder();
//		sp = getSharedPreferences("palymode.cfg", MODE_PRIVATE);
//
//		playMode = getPlayModeFromSp();
//
//	}
//
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		if(intent!=null){
//			boolean isFromNotification = intent.getBooleanExtra("isFromNotification", false);
//			if(isFromNotification){
//				//从通知startService
//				int view_action = intent.getIntExtra("view_action", -1);
//				switch (view_action) {
//				case VIEW_PRE:
//					audioPlayBinder.playPre();
//					break;
//				case VIEW_NEXT:
//					audioPlayBinder.playNext();
//					break;
//				case VIEW_CONTAINER:
//					notifyPrepared();
//					break;
//				}
//			}else {
//				//从activity中startService
//				currentPosition = intent.getIntExtra("currentPosition", -1);
//				audioList = (ArrayList<AudioItem>) intent.getSerializableExtra("audioList");
//				audioPlayBinder.playAudio();
//			}
//
//		}
//
//		return START_STICKY;//服务被杀死后会自动重启
//	}
//
//	public class AudioPlayBinder extends Binder{
//
//		/**
//		 * 播放音乐
//		 */
//		public void playAudio(){
//			if(mediaPlayer!=null){
//				mediaPlayer.release();
//				mediaPlayer = null;
//			}
//			audioItem = audioList.get(currentPosition);
//			try {
//				mediaPlayer = new MediaPlayer();
//				mediaPlayer.setOnPreparedListener(mOnPreparedListener);
//				mediaPlayer.setOnCompletionListener(mOnCompletionListener);
//				mediaPlayer.setDataSource(audioItem.getPath());//设置数据源，会进入init状态
//				mediaPlayer.prepareAsync();//异步准备，进入preparing状态
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		/**
//		 * 是否正在播放
//		 * @return
//		 */
//		public boolean isPlaying(){
//			return mediaPlayer!=null?mediaPlayer.isPlaying():false;
//		}
//
//		public void pause(){
//			if(mediaPlayer!=null){
//				mediaPlayer.pause();
//			}
//			stopForeground(true);//取消通知
//		}
//		public void start(){
//			if(mediaPlayer!=null){
//				mediaPlayer.start();
//			}
//			sendNotification();
//		}
//
//		public long getDuration(){
//			return mediaPlayer!=null?mediaPlayer.getDuration():0;
//		}
//
//		public long getCurrentPosition(){
//			return mediaPlayer!=null?mediaPlayer.getCurrentPosition():0;
//		}
//		public void seekTo(int position){
//			if(mediaPlayer!=null){
//				mediaPlayer.seekTo(position);
//			}
//		}
//
//		public void playNext(){
//			if(currentPosition<(audioList.size()-1)){
//				currentPosition++;
//				playAudio();
//			}
//		}
//		public void playPre(){
//			if(currentPosition>0){
//				currentPosition--;
//				playAudio();
//			}
//		}
//
//		public boolean isPlayingFirst(){
//			return currentPosition==0;
//		}
//		public boolean isPlayingLast(){
//			return currentPosition==(audioList.size()-1);
//		}
//
//		/**
//		 * 三中播放模式进行切换
//		 */
//		public void switchPlayMode(){
//			switch (playMode) {
//			case MODE_RANDOM://如果当前是随机，则下次应该切换为单曲循环
//				playMode = MODE_SINGLE_REPEAT;
//				break;
//			case MODE_SINGLE_REPEAT://如果当前是单曲循环，则下次应该切换为循环播放
//				playMode = MODE_ALL_REPEAT;
//				break;
//			case MODE_ALL_REPEAT://如果当前是循环播放，则下次应该切换为随机播放
//				playMode = MODE_RANDOM;
//				break;
//			}
//			savePlayModeToSp();
//		}
//		/**
//		 * 获取当前的播放模式
//		 * @return
//		 */
//		public int getPlayMode(){
//			return playMode;
//		}
//	}
//
//	private OnPreparedListener mOnPreparedListener = new OnPreparedListener() {
//		@Override
//		public void onPrepared(MediaPlayer mp) {
//			mediaPlayer.start();
//			sendNotification();
//			notifyPrepared();
//		}
//	};
//
//
//	/**
//	 * 当准备完成时候，发送广播
//	 */
//	private void notifyPrepared(){
//		Intent intent = new Intent(ACTION_PREPARED);
//		intent.putExtra("audioItem", audioList.get(currentPosition));
//		sendBroadcast(intent);
//	}
//	private OnCompletionListener mOnCompletionListener = new OnCompletionListener() {
//		@Override
//		public void onCompletion(MediaPlayer mp) {
//			notifyCompletion();
//			autoPlayByMode();
//		}
//	};
//
//	/**
//	 * 自动根据播放模式而播放
//	 */
//	private void autoPlayByMode(){
//		switch (playMode) {
//		case MODE_RANDOM://从0-audioList.size()-1随机播放一首
//			Random random = new Random();
//			currentPosition = random.nextInt(audioList.size());
//			audioPlayBinder.playAudio();
//			break;
//		case MODE_SINGLE_REPEAT://继续播放当前位置的歌曲
//			audioPlayBinder.playAudio();
//			break;
//		case MODE_ALL_REPEAT://从0到最后，最后一首播放完再从0-最后
//			if(currentPosition==(audioList.size()-1)){
//				//如果当前是最后一首，则应该从0开始播
//				currentPosition = 0;
//			}else {
//				currentPosition++;
//			}
//			audioPlayBinder.playAudio();
//			break;
//		}
//	}
//
//	/**
//	 * 当播放完成时候，发送广播
//	 */
//	private void notifyCompletion(){
//		Intent intent = new Intent(ACTION_COMPLETION);
//		intent.putExtra("audioItem", audioList.get(currentPosition));
//		sendBroadcast(intent);
//	}
//
//	private void savePlayModeToSp(){
//		sp.edit().putInt("playMode", playMode).commit();
//	}
//
//	private int getPlayModeFromSp(){
//		return sp.getInt("playMode", MODE_RANDOM);
//	}
//
//	private void sendNotification(){
//		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//		builder.setSmallIcon(R.drawable.notification_music_playing)
//		 	   .setTicker("正在播放："+StringUtil.formatAudioName(audioItem.getTitle()))
//		 	   .setWhen(System.currentTimeMillis())
//		 	   .setOngoing(true);
//
//
//		if(VERSION.SDK_INT>10){
//			//3.0以后才支持自定义布局的通知
//			builder.setContent(getRemoteViews());//自定义布局
//		}else {
//			//3.0之前只能使用普通的通知了
//			Intent intent = new Intent("com.example.customlayoutnotification");
//			PendingIntent contentIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//			builder.setContentIntent(contentIntent)//系统定义的布局
//				   .setContentTitle(StringUtil.formatAudioName(audioItem.getTitle()))
//				   .setContentText(audioItem.getArtist());
//		}
//		startForeground(1, builder.build());//发送通知
//	}
//	private RemoteViews getRemoteViews(){
//		RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_audio_notification);
//		remoteViews.setTextViewText(R.id.notification_name, StringUtil.formatAudioName(audioItem.getTitle()));
//		remoteViews.setTextViewText(R.id.notification_artist, audioItem.getArtist());
//		remoteViews.setOnClickPendingIntent(R.id.notification_pre, getPrePendingIntent());
//		remoteViews.setOnClickPendingIntent(R.id.notification_next, getNextPendingIntent());
//		remoteViews.setOnClickPendingIntent(R.id.notification_container, getContainerPendingIntent());
//		return remoteViews;
//	}
//	private PendingIntent getPrePendingIntent(){
//		Intent intent = new Intent(this,AudioPlayService.class);
//		intent.putExtra("view_action", VIEW_PRE);
//		intent.putExtra("isFromNotification", true);
//		PendingIntent contentIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		return contentIntent;
//	}
//	private PendingIntent getNextPendingIntent(){
//		Intent intent = new Intent(this,AudioPlayService.class);
//		intent.putExtra("view_action", VIEW_NEXT);
//		intent.putExtra("isFromNotification", true);
//		PendingIntent contentIntent = PendingIntent.getService(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		return contentIntent;
//	}
//	private PendingIntent getContainerPendingIntent(){
//		Intent intent = new Intent(this,AudioPlayerActivity.class);
////		intent.putExtra("which", 3);
//		intent.putExtra("isFromNotification", true);
//		intent.putExtra("view_action", VIEW_CONTAINER);
//		PendingIntent contentIntent = PendingIntent.getActivity(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		return contentIntent;
//	}
//}
