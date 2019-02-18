package com.emotte.order.order.service;

import java.util.List;

import com.emotte.order.order.model.Recorder;

public interface RecorderService {

	public Recorder loadRecorder(Long id);

	public List<Recorder> queryRecorder(Recorder recorder);

	public void insertRecorder(Recorder recorder);

	public void updateRecorder(Recorder recorder);

	public void queryRecorderinfo();

}