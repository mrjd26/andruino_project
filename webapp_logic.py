import requests
from django.shortcuts import render_to_response

def andruino(request):

	if request.GET['state']:
		state=request.GET['state']
	else:
		state=None

	myip="67.188.178.196"
	myport="29876"

	url="http://"+myip+":"+myport

	params={"state":state}



	r = requests.get(url,params=params)

	content=r.content
	url=r.url
	reason=r.reason


	return render_to_response("andruino.html",{"content":content,"url":url,"reason":reason,"state":state})
