package com.example.gilbertojimenezorench.myapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;


public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        private final Context mContext;
        private final View.OnClickListener mExpandCollapseListener;
        public View primaryActionView;
        protected TextView vName;
        protected TextView vAddress;
        protected TextView cTel;
        protected TextView cWeb;
        public String web;
        public String tel;
        public int rowId;
        public View actionsView;
        public CardView cardView;
        public ViewStub stub;

        public RestaurantViewHolder(Context context, View.OnClickListener expandCollapseListener, View v, View primaryActionView, CardView cardView, TextView vName, TextView vAddress) {
                super(v);
                this.mContext = context;
                this.mExpandCollapseListener = expandCollapseListener;
                this.rootView = v;
                this.primaryActionView = primaryActionView;
                this.cardView = cardView;
                this.vName = vName;
                this.vAddress = vAddress;

                primaryActionView.setOnClickListener(mExpandCollapseListener);
        }

        public void showActions(boolean show) {
                if (show) {
                        // Inflate the view stub if necessary, and wire up the event handlers.
                        inflateActionViewStub();

                        actionsView.setVisibility(View.VISIBLE);
                        actionsView.setAlpha(1.0f);
                } else {
                        // When recycling a view, it is possible the actionsView ViewStub was previously
                        // inflated so we should hide it in this case.
                        if (actionsView != null) {
                                actionsView.setVisibility(View.GONE);
                        }
                }
        }

        private void inflateActionViewStub() {
                ViewStub stub = (ViewStub) rootView.findViewById(R.id.viewStub);
                if (stub != null) {
                        actionsView = stub.inflate();
                        cTel = (TextView) actionsView.findViewById(R.id.tel_text);
                        cWeb = (TextView) actionsView.findViewById(R.id.web_text);
                }
                cTel.setText(tel == null ? "" : tel);
                cWeb.setText(web == null ? "" : web);
        }

        public static RestaurantViewHolder create(
                View v,
                Context context,
                View.OnClickListener expandCollapseListener) {

                        return new RestaurantViewHolder(
                                context,
                                expandCollapseListener,
                                v,
                                v.findViewById(R.id.primary_action_view),
                                (CardView) v.findViewById(R.id.card_view),
                                (TextView) v.findViewById(R.id.info_text),
                                (TextView) v.findViewById(R.id.description));

                }
}
