package com.example.sadas;

import java.util.List;
import java.util.Map;

import com.example.sadas.DownImage.ImageCallBack;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater layoutInflater;
	private List<Map<String,Object>> list;
	
	public MyAdapter(Context context) {
		
		this.context = context;
		layoutInflater = layoutInflater.from(context);
	}
	
	public List getData(){
		return list;
	}

	public void setData(List<Map<String,Object>> data){
		this.list = data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		final ViewHolder viewHolder;
		
		if (convertView == null ) {
			convertView = layoutInflater.inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.id = (TextView)convertView.findViewById(R.id.textView1);
			viewHolder.name = (TextView)convertView.findViewById(R.id.textView2);
			viewHolder.address = (TextView)convertView.findViewById(R.id.textView3);
			viewHolder.img = (ImageView)convertView.findViewById(R.id.imageView1);
			
			convertView.setTag(viewHolder);
			
		}else{
			
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		viewHolder.id.setText(list.get(position).get("sid").toString());
		viewHolder.name.setText(list.get(position).get("name").toString());
		viewHolder.address.setText(list.get(position).get("addr").toString());
		
		
		//�ӿڻص��ķ��������ͼƬ�Ķ�ȡ;
		DownImage downImage = new DownImage(HttpUtil.IMG_URL+list.get(position).get("pic").toString()+".jpg");
		downImage.loadImage(new ImageCallBack() {
			
			@Override
			public void getDrawable(Drawable drawable) {
				// TODO Auto-generated method stub
				viewHolder.img.setImageDrawable(drawable);
			}
		});
		
		
		return convertView;
	}
	
}
