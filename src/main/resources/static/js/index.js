$(function() {
	queryNodeToJSON();
});

var JsonData = {nodes:[],links:[]};

var simulation = null;

/**
 * 根据node节点名称查询数据,返回JSON
 * 
 * @returns
 */
function queryNodeToJSON(key) {
	if(key){
		$("#keyword").val(key);
	}
	var keyword = $("#keyword").val();
	if (keyword) {
		 $.get("searchKey", {nodeName : keyword}, function(data) {
			 JsonData.nodes = data;
			 for ( var i in data) {
				if(data[i].keywords && data[i].keywords.length>0){
					JsonData.links.push({'source':data[i].id,'target':data[i].keywords[0].id});
				}
			 }
			initD3(JsonData);
		 });
	} else {
		layer.alert('请输入关键字!');
	}
}

/**
 * 初始化加载数据
 * https://bl.ocks.org/mbostock/2675ff61ea5e063ede2b5d63c08020c7
 * 
 * @returns
 */
function initD3(JsonData) {

	if (JsonData.nodes.length == 0) {
		layer.alert('数据采集中,暂时没有发现相关信息!');
		return false;
	}
	
	//清空画布
	$(".d3-boy svg").remove();
	if(simulation){
		simulation.stop();
	}
	
	//初始化大小
	var width = 1280, height = 800;

	//初始化画布大小
	var svg = d3.select(".d3-boy").append("svg:svg")
				.attr("width", width)
				.attr("height", height);
	 

	//初始化力导向图模型参数
	simulation = d3.forceSimulation()
			.force("charge", d3.forceManyBody()) //节点相互之间的作用力
			.force("center",d3.forceCenter(width / 2+50, height / 2-50)) //设置中心点位置
			.force("link",d3.forceLink()
					.id(function(d) { return d.id; }) //设置link 的id
					.distance(function(d){return 300;}) //设置link的作用距离
			 );
	
	//选择连线,初始化数据 
	var link = svg.append("g")
				  .attr("class", "links")
				  .selectAll("line")
				  .data(JsonData.links)
				  .enter().append("line");

	//选中节点，初始化数据，绑定drag（拖拽）参数
	var node = svg.append("g")
				  .attr("class", "layer nodes")
				  .selectAll("g")
				  .data(JsonData.nodes)
				  .enter().append("g").attr("class","node_g")
				  .call(
				     d3.drag().on("start", dragstarted)
				              .on("drag", dragged)
						      .on("end", dragended)
				  );
	

	//每个node下追加circle元素
	node.append("circle")
		.attr("class","node_circle")
		.attr("r", 15)
		.attr("title",function(d){
				return d.name;
			});
	node.append("text").text(function(d){
			return d.name.substr(0,3)+"...";
		});

	simulation.nodes(JsonData.nodes).on("tick", ticked);

	simulation.force("link").links(JsonData.links);

	//节点拖拽后，其他节点的相关坐标变化
	function ticked() {
		link.attr("x1", function(d) {
			return d.source.x;
		}).attr("y1", function(d) {
			return d.source.y;
		}).attr("x2", function(d) {
			return d.target.x;
		}).attr("y2", function(d) {
			return d.target.y;
		});
		

		node.attr("cx", function(d) {
			return d.x;
		}).attr("cy", function(d) {
			return d.y;
		}).attr("transform",function(d){
			return "translate("+d.x+","+d.y+")";
		});
	}


	function dragstarted(d) {
		if (!d3.event.active)
			simulation.alphaTarget(0.3).restart();
		d.fx = d.x;
		d.fy = d.y;
	}

	function dragged(d) {
		d.fx = d3.event.x;
		d.fy = d3.event.y;
	}

	function dragended(d) {
		if (!d3.event.active)
			simulation.alphaTarget(0);
		d.fx = null;
		d.fy = null;
	}
}
