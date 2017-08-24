<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/zTree_v3-master/js/jquery.ztree.core.js"></script>
	
	
	<style type="text/css">
		
		ul#menu_right{
			position:absolute;
			display: none;
			list-style: none;
			background-color: pink;
			width: auto;
			padding: 0px;
			font-size: small;
		}
		
		ul#menu_right li{
		     cursor: pointer;
		}
		ul#menu_right li:HOVER{
		     color: gray;
		}
	</style>
</head>
<body>
		<div id="tree_wrapper" style="background-color: #d0ddd0"  ondblclick="expandOrCollapseAll()"  onclick="hideRightMenu()">
				<ul id="ztree" class="ztree"></ul>
		</div>
		
		
		    <ul id="menu_right">
		    	<li id="li_delete1" onclick="deleteNode1()">删除节点</li>
		    	<li id="li_delete2" onclick="deleteNode2()">删除节点(包含子节点)</li>
		    	<li id="li_delete3" onclick="deleteNode3()">删除全部子节点</li>
		    	<li id="li_rename" onclick="renameNode()">重命名节点</li>
		    	<li id="li_addRootNode" onclick="addNode(1)">添加根节点</li>
		    	<li id="li_add" onclick="addNode(0)">添加子节点</li>
		    </ul>
</body>
<script type="text/javascript">
		var ztreeObj;
		var isExpand=false;
		var setting = {
			view: {
				showLine: false,
				showIcon: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				onRightClick:function(event, treeId, treeNode) {
						 if(treeNode!=null){
							 ztreeObj.selectNode(treeNode, false, false);
						     $("#menu_right").show();
						     $("#menu_right").css("left",event.clientX+"px").css("top",event.clientY+"px");
						 }
				},
				onClick:function(event, treeId, treeNode) {//单击节点事件
						if(treeNode.open){
							ztreeObj.expandNode(treeNode, false, false, true, false);
						}else{
							ztreeObj.expandNode(treeNode, true, false, true, false);
						}
						$("#menu_right").hide();
				}
			}
		};
		
		/* var zNodes =[{ id:3, pId:0, name:"xxx",open:true, isParent:true}]; */
		
		$(document).ready(function(){
			$.ajax({ 
					type:"GET", 
					url:"${pageContext.request.contextPath}/loadCategorys.do", 
					dataType:"json", 
					success:function(data){ 
						ztreeObj=$.fn.zTree.init($("#ztree"), setting, data);
					} 
			}); 
			
			
           //去掉默认的contextmenu事件，否则会和右键事件同时出现。
            document.getElementById("tree_wrapper").oncontextmenu = function(e){
               e.preventDefault();
           };
           document.getElementById("tree_wrapper").onmousedown = function(e){
               if(e.button ==2){
                //   alert("你点了右键");
               }else if(e.button ==0){
                  // alert("你点了左键");
               }else if(e.button ==1){
                   //alert("你点了滚轮");
               }
           }
		});
		
		
		function expandOrCollapseAll(){//展开/折叠整棵树
			if(isExpand){
				ztreeObj.expandAll(false);
				isExpand=false;
			}else{
				ztreeObj.expandAll(true);
				isExpand=true;
			}
		}
		
		
		function hideRightMenu(){//隐藏鼠标右键菜单
			 $("#menu_right").hide();
		}
		
    	
    	function deleteNode1(){
    	}
    	
    	function deleteNode2(){
    	}
    	
    	function deleteNode3(){
    	}
    	
    	
    	function renameNode(){
    			var currNode=parentNode=ztreeObj.getSelectedNodes()[0];	 
    			var newName=window.prompt("请输入您为类别“"+currNode.name+"”选取的新名称！", currNode.name);
    			if(newName){
		    			 newName= newName.replace(/(^\s*)|(\s*$)/g, "");
		    			 $.ajax({ 
								type:"POST", 
								url:"${pageContext.request.contextPath}/updateVideoCategory.do", 
								dataType:"json", 
								data:{videoCategoryName:newName,id:currNode.id},
								success:function(id){ 
										alert("恭喜您，类别“"+currNode.name+"”成功修改为“"+newName+"”!");
										window.location.href="${pageContext.request.contextPath}/viewCategory.do";
								} 
					     });
    			}
    		
    	}
    	
    	function addNode(isRoot){
	    		var name=window.prompt("请输入新节点名称！", "");
	    		if(name){
		    			 name= name.replace(/(^\s*)|(\s*$)/g, "");
		    			 var parentNode=null;
		    			 var pid=-1;
		    			 if(isRoot==1){//添加跟节点
		    				    var rootNodes = ztreeObj.getNodes();
		    			        for(var index in rootNodes){
			    			    	    var currRootNode=rootNodes[index];
			    			    	    if(currRootNode.name==name){
			    			    	    	alert("对不起，根类别“"+name+"”已存在！");
			    			    	    	hideRightMenu();
			    			    	    	return;
			    			    	    }
		    			        }
		    			 }else{//添加子节点
		    				  //看当前节点下是否已经存在此类别名称
		    	 			   parentNode=ztreeObj.getSelectedNodes()[0];
		    	 			   pid=parentNode.id;
		    			 }
		    			 
		    			 if(parentNode!=null){
		    				   var childNodes=ztreeObj.getNodesByParam("name",name,parentNode);
		    			 	   if(childNodes.length>0){
		    			 		 		alert("对不起，类别“"+name+"”已存在！");
		    			 		 		hideRightMenu();
		    			    	    	return;
		    			 	   }
		    			 }
		    			 
		    			 $.ajax({ 
								type:"POST", 
								url:"${pageContext.request.contextPath}/addVideoCategory.do", 
								dataType:"json", 
								data:{videoCategoryName:name,parentId:pid},
								success:function(id){ 
										//alert("新添加的子节点id="+id);
										if(id>0){
											 ztreeObj.addNodes(parentNode, -1, [{ id:id,name:name,open:false, isParent:false}], false);
											 alert("恭喜您，类别“"+name+"”添加成功！");
										}else{
										     alert("非常抱歉，类别“"+name+"”添加失败！");
										}
								} 
					     });
	    		}
	    		hideRightMenu();
    	}
    	
		
	    
</script>
</html>