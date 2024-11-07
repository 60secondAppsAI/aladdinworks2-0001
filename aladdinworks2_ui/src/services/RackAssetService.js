import http from "../http-common"; 

class RackAssetService {
  getAllRackAssets(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/rackAsset/rackAssets`, searchDTO);
  }

  get(rackAssetId) {
    return this.getRequest(`/rackAsset/${rackAssetId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/rackAsset?field=${matchData}`, null);
  }

  addRackAsset(data) {
    return http.post("/rackAsset/addRackAsset", data);
  }

  update(data) {
  	return http.post("/rackAsset/updateRackAsset", data);
  }
  
  uploadImage(data,rackAssetId) {
  	return http.postForm("/rackAsset/uploadImage/"+rackAssetId, data);
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

export default new RackAssetService();
