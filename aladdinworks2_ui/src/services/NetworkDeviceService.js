import http from "../http-common"; 

class NetworkDeviceService {
  getAllNetworkDevices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/networkDevice/networkDevices`, searchDTO);
  }

  get(networkDeviceId) {
    return this.getRequest(`/networkDevice/${networkDeviceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/networkDevice?field=${matchData}`, null);
  }

  addNetworkDevice(data) {
    return http.post("/networkDevice/addNetworkDevice", data);
  }

  update(data) {
  	return http.post("/networkDevice/updateNetworkDevice", data);
  }
  
  uploadImage(data,networkDeviceId) {
  	return http.postForm("/networkDevice/uploadImage/"+networkDeviceId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new NetworkDeviceService();
