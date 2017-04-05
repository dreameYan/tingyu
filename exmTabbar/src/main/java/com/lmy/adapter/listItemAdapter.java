package com.lmy.adapter;

import java.util.List;

import com.example.exmtabbar.R;
import com.lmy.bean.listening;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class listItemAdapter extends BaseAdapter {
	
	private  Context context;
    private  LayoutInflater inflater;
    private  List<listening> mList;
    public  listItemAdapter(Context context, List<listening> b) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mList = b;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView==null){

            convertView = inflater.inflate(R.layout.listview_item,null);
            viewHolder  = new ViewHolder();
            /**得到各个控件的对象*/
           /* viewHolder.bt_Two = (Button)convertView.findViewById(R.id.bt_two);
            viewHolder.tv_title2=(TextView)convertView.findViewById(R.id.tv_title2);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            viewHolder.tv_timend=(TextView)convertView.findViewById(R.id.tv_timeend);
*/
            convertView.setTag(viewHolder);//绑定ViewHolder对象
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
		
		viewHolder.tv_title2.setText(mList.get(position).getTitle().toString());
        viewHolder.tv_time.setText("材料来源："+mList.get(position).getStartTime().toString());
        viewHolder.tv_timend.setText("句数总计：" + mList.get(position).getEndTime().toString());
        
		return convertView;
	}
	
	
	
	class ViewHolder  {
		private TextView tv_title2;
        private TextView tv_time;
        private TextView tv_timend;
        private Button bt_Two;
	}

}
