/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.kcwdev.downloader;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.json.JSONArray;

import android.os.Handler;
import android.os.Message;

@Kroll.module(name="Downloader", id="com.kcwdev.downloader")
public class DownloaderModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "DownloaderModule";
	private static final boolean DBG = TiConfig.LOGD;

	private ProgressiveDownloader downloader;
	private DownloaderModule self;
	
	public static final String EVENT_PROGRESS = "progress";
	public static final String EVENT_PAUSED = "paused";
	public static final String EVENT_FAILED = "failed";
	public static final String EVENT_COMPLETED = "completed";
	public static final String EVENT_CANCELLED = "cancelled";
	public static final String EVENT_STARTED = "started";
	public static final String EVENT_BATCHPAUSED = "batchpaused";
	public static final String EVENT_BATCHFAILED = "batchfailed";
	public static final String EVENT_BATCHCOMPLETED = "batchcompleted";
	public static final String EVENT_BATCHCANCELLED = "batchcancelled";
	
	@Kroll.constant public static final int NETWORK_TYPE_WIFI = 0;
	@Kroll.constant public static final int NETWORK_TYPE_MOBILE = 1;
	@Kroll.constant public static final int NETWORK_TYPE_ANY = 2;

	@Kroll.constant public static final int DOWNLOAD_PRIORITY_LOW = 1;
	@Kroll.constant public static final int DOWNLOAD_PRIORITY_NORMAL = 2;
	@Kroll.constant public static final int DOWNLOAD_PRIORITY_HIGH = 3;
	
	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;
	
	public DownloaderModule() {
		super("Downloader");
		downloader = new ProgressiveDownloader(TiApplication.getAppRootOrCurrentActivity());
		downloader.setMaximumSimultaneousDownloads(2);
		downloader.DownloadProgress.addListener(new ProgressListener());
		downloader.DownloadPaused.addListener(new PausedListener());
		downloader.DownloadFailed.addListener(new FailedListener());
		downloader.DownloadCompleted.addListener(new CompletedListener());
		downloader.DownloadCancelled.addListener(new CancelledListener());
		downloader.DownloadStarted.addListener(new StartedListener());
		downloader.DownloadBatchPaused.addListener(new BatchPausedListener());
		downloader.DownloadBatchFailed.addListener(new BatchFailedListener());
		downloader.DownloadBatchCompleted.addListener(new BatchCompletedListener());
		downloader.DownloadBatchCancelled.addListener(new BatchCancelledListener());
		
		this.self = this;
	}
	
//	private static final int MSG_FIRE_PROGRESS = 70000;
//	private static final int MSG_FIRE_PAUSED = 70001;
//	private static final int MSG_FIRE_FAILED = 70002;
//	private static final int MSG_FIRE_COMPLETED = 70003;
//	private static final int MSG_FIRE_CANCELLED = 70004;
//	private final Handler handler = new Handler(TiApplication.getInstance().getMainLooper(), new Handler.Callback() {
//		
//		@Override
//		public boolean handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			
//			String eventToFire = "";
//			if (msg.what == MSG_FIRE_PROGRESS)
//			{
//				eventToFire = EVENT_PROGRESS;
//			} 
//			else if (msg.what == MSG_FIRE_PAUSED)
//			{
//				eventToFire = EVENT_PAUSED;				
//			}
//			else if (msg.what == MSG_FIRE_FAILED)
//			{
//				eventToFire = EVENT_FAILED;				
//			}
//			else if (msg.what == MSG_FIRE_COMPLETED)
//			{
//				eventToFire = EVENT_COMPLETED;				
//			}
//			else if (msg.what == MSG_FIRE_CANCELLED)
//			{
//				eventToFire = EVENT_CANCELLED;				
//			}
//			
//			if (eventToFire.length() > 0)
//			{
//				self.fireEvent(eventToFire, msg.obj);
//				return true;
//			}
//			
//			
//			return false;
//		}
//	});	
	
	
	class ProgressListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// Log.d(LCAT, "Download Progress " + event.getDownloadInformation().getAvailableLength() + "/" + event.getDownloadInformation().getLength());
			KrollDict dict = createDict(event.getDownloadInformation());
			self.fireEvent(EVENT_PROGRESS, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_PROGRESS, dict));
		}
	}
	class PausedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			Log.d(LCAT, "Download Paused ");
			KrollDict dict = createDict(event.getDownloadInformation());
			self.fireEvent(EVENT_PAUSED, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_PAUSED, dict));
		}
	}
	class FailedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			Log.d(LCAT, "Download Failed ");
			KrollDict dict = createDict(event.getDownloadInformation());
			self.fireEvent(EVENT_FAILED, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_FAILED, dict));
		}
	}
	class CompletedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			Log.d(LCAT, "Download Completed ");
			KrollDict dict = createDict(event.getDownloadInformation());			
			self.fireEvent(EVENT_COMPLETED, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_COMPLETED, dict));
		}
	}
	class CancelledListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			Log.d(LCAT, "Download Cancelled ");	
			KrollDict dict = createDict(event.getDownloadInformation());
			self.fireEvent(EVENT_CANCELLED, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_CANCELLED, dict));
		}
	}

	class StartedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			Log.d(LCAT, "Download Started ");	
			KrollDict dict = createDict(event.getDownloadInformation());
			dict.put("reason", event.getDownloadInformation().getMessage());
			self.fireEvent(EVENT_CANCELLED, dict);
			//TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_FIRE_CANCELLED, dict));
		}
	}
	
	class BatchPausedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			//Log.d(LCAT, "Download Batch Paused ");
		}
	}
	class BatchFailedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			//Log.d(LCAT, "Download Batch Failed ");
		}
	}
	class BatchCompletedListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			//Log.d(LCAT, "Download Batch Completed ");
		}
	}
	class BatchCancelledListener implements IListener<DownloadEvent>
	{
		@Override
		public void handleEvent(DownloadEvent event) {
			// TODO Auto-generated method stub
			//Log.d(LCAT, "Download Batch Cancelled ");
		}
	}
	

	@Kroll.getProperty
	public int getMaximumSimultaneousDownloads() {
		return downloader.getMaximumSimultaneousDownloads();
	}

	@Kroll.setProperty @Kroll.method
	public void setMaximumSimultaneousDownloads(int count) {
		downloader.setMaximumSimultaneousDownloads(count);
	}	
	
	@Kroll.getProperty
	public int getPermittedNetworkTypes() {
		EnumSet<NetworkTypes> types = downloader.getPermittedNetworkTypes();
		if (types.containsAll(NetworkTypes.Any)) {
			return NETWORK_TYPE_ANY;
		} else if (types.containsAll(NetworkTypes.Mobile)) {
			return NETWORK_TYPE_MOBILE;
		} else if (types.contains(NetworkTypes.Wireless80211)) {
			return NETWORK_TYPE_WIFI;
		}
		
		return -1;
	}

	@Kroll.setProperty @Kroll.method
	public void setPermittedNetworkTypes(int type) {
		if (type == NETWORK_TYPE_ANY) {
			downloader.setPermittedNetworkTypes(NetworkTypes.Any);
		} else if (type == NETWORK_TYPE_WIFI) {
			downloader.setPermittedNetworkTypes(EnumSet.of(NetworkTypes.Wireless80211));
		} else if (type == NETWORK_TYPE_MOBILE) {
			downloader.setPermittedNetworkTypes(NetworkTypes.Mobile);
		}
	}
	// Methods
	@Kroll.method
	public void addDownload(KrollDict dict) {
		DownloadRequest request = new DownloadRequest();
		request.setUrl(TiConvert.toString(dict, "url"));
		request.setName(TiConvert.toString(dict, "name"));
		request.setLocale("eng");
		request.setFileName(TiConvert.toString(dict, "filePath"));
		
		int priority = 0;
		if (dict.containsKey("priority")) {
			priority = TiConvert.toInt(dict, "priority");
		}
		switch(priority) {
			case 1:  request.setDownloadPriority(DownloadPriority.Low); break;
			case 3:  request.setDownloadPriority(DownloadPriority.High); break;
			default: request.setDownloadPriority(DownloadPriority.Normal); break;
		}
		
		downloader.download(request);
	}
	
	@Kroll.method
	public void pauseAll() {
		downloader.pause();
	}
	
	@Kroll.method
	public void pauseItem(String url) {
		downloader.pause(url);
	}
	
	@Kroll.method
	public void resumeAll() {
		downloader.resume();
	}
	
	@Kroll.method
	public void resumeItem(String url) {
		downloader.resume(url);
	}
	
	@Kroll.method
	public void cancelItem(String url) {
		downloader.cancel(url);
	}
	
	@Kroll.method
	public void stopDownloader() {
		downloader.stop();
	}
	
	@Kroll.method
	public void restartDownloader() {
		downloader.start();		
	}
	
	@Kroll.method
	public void deleteItem(String url) {
		downloader.delete(url);
	}
	
	@Kroll.method
	public KrollDict getDownloadInfo(String url) {
		
		DownloadInformation di = downloader.getDownloadInformation(url);
		if (di == null)
		{
			return null;
		}
		
		return createDict(di);
	}
	
	@Kroll.method
	public KrollDict[] getAllDownloadInfo() {
		ArrayList<KrollDict> list = new ArrayList<KrollDict>();
		for (DownloadInformation di : downloader.getDownloadInformation()) {
			list.add(createDict(di));
		}
		
		KrollDict[] array = new KrollDict[0];
				
		return list.toArray(array);
	}

	private KrollDict createDict(DownloadInformation di) {
		KrollDict dict = new KrollDict();
		dict.put("name", di.getName());
		dict.put("url", di.getUrl());
		dict.put("downloadedBytes", di.getAvailableLength());
		dict.put("totalBytes", di.getLength());
		dict.put("bps", di.getLastDownloadBitsPerSecond());
		dict.put("filePath", di.getFilePath());
		dict.put("createdDate", di.getCreationUtc());
		dict.put("priority", di.getDownloadPriority().getValue());
		return dict;
	}
}