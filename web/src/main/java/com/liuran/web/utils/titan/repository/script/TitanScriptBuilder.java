package com.liuran.web.utils.titan.repository.script;

import com.liuran.web.utils.titan.repository.model.TitanModel;
import com.liuran.web.utils.titan.repository.utils.ParseAnnotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TitanScriptBuilder {
    private StringBuilder script = new StringBuilder();
    private Map<String, Object> param = new HashMap<>();
    private List<String> methodNames = new ArrayList<>();
    private HashSet<ScriptMethod> scriptMethodSet = new HashSet<>();

    private class ScriptResult{
        private String invokeMethod;
        private String resultName;
        private ScriptMethod scriptMethod;

        public ScriptResult(ScriptMethod scriptMethod){
            this.scriptMethod = scriptMethod;
        }

        public String invokeMethod() {
            return invokeMethod;
        }

        public void setInvokeMethod(String invokeMethod) {
            this.invokeMethod = invokeMethod;
        }

        public String getResultName() {
            return resultName;
        }

        public void setResultName(String resultName) {
            this.resultName = resultName;
        }

        public ScriptMethod getScriptMethod() {
            return scriptMethod;
        }

    }

    enum  ScriptMethod{
        DELETE_EDGE_BY_IDENTIFIER("void","deleteEdgeByIdentifier"),
        ADD_VERTEX_ESP("Vertex","addVertexEsp"),
        UPDATE_PROPERTIES("void","updateProperties"),
        DELETE_PROPERTIES("void","deleteProperties"),
        ADD_EDGE_ESP("Edge","addEdgeEsp"),
        CHECK_ELEMENT_EXIST("boolean","checkElementExist"),
        SCRIPT_COMMON_METHOD("DefaultGraphTraversal","scriptCommonMethod"),
        GET_ELEMENT("Element","getElement"),
        DELETE_ELEMENT("void","deleteElement"),
        ADD_OR_UPDATE_VERTEX("void","addOrUpdateVertex"),
        ADD_VERTEX_BEFORE_CHECK("void","addVertexBeforeCheck"),
        ADD_EDGE("void","addEdge"),
        ADD_EDGE_BEFORE_CHECK("void","addEdgeBeforeCheck"),
        ADD_OR_UPDATE_EDGE("void","addOrUpdateEdge"),
        UPDATE_ELEMENT("void","updateElement"),
        DATA_TRANSFER("void","dataTransfer")
        ;

        final static String RESULT_PRE = "result";
        private static int methodIndex = 0;

        private String methodName;
        private String script;
        private String resultType;


        ScriptMethod(String resultType, String methodName){
            this.resultType = resultType;
            this.methodName = methodName;
            this.script = loadScript(methodName);
        }

        public static void clean(){
            methodIndex = 0;
        }
        private static String loadScript(String fileName){
            String name = "/titanscript/"+fileName+".script";
            BufferedReader reader = null;
            StringBuilder script = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(ScriptMethod.class.getResourceAsStream(name)));
                String line;
                while ((line = reader.readLine())!=null){
                    line = line.trim();
                    if (line.startsWith("//")){
                        continue;
                    }
                    script.append(line);
                }
            } catch (IOException e) {
//                LOG.error("读取脚本文件{}出错",name);
            } finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return script.toString().replace("\n","").replace("\t","");
        }

    }

    private ScriptResult deleteEdgeByIdentifierM(String identifier){
        ScriptResult result = new ScriptResult(ScriptMethod.DELETE_EDGE_BY_IDENTIFIER);
        result.setInvokeMethod(ScriptMethod.DELETE_EDGE_BY_IDENTIFIER.methodName +"("+identifier+")");
        return result;
    }

    private ScriptResult addVertexEspM(String label, String values){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_VERTEX_ESP);
        result.setInvokeMethod(ScriptMethod.ADD_VERTEX_ESP.methodName +"("+label+","+values+")");
        return result;
    }

    private ScriptResult updatePropertiesM(String element, String values){
        ScriptResult result = new ScriptResult(ScriptMethod.UPDATE_PROPERTIES);
        result.setInvokeMethod(ScriptMethod.UPDATE_PROPERTIES.methodName +"("+element+","+values+")");
        return result;
    }

    private ScriptResult deletePropertiesM(String element, String propertyList){
        ScriptResult result = new ScriptResult(ScriptMethod.DELETE_PROPERTIES);
        result.setInvokeMethod(ScriptMethod.DELETE_PROPERTIES.methodName +"("+element+","+propertyList+")");
        return result;
    }

    private ScriptResult addEdgeEspM(String source, String target, String label, String values){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_EDGE_ESP);
        result.setInvokeMethod(ScriptMethod.ADD_EDGE_ESP.methodName +"("+source+","+target+","+label+","+values+")");
        return result;
    }

    private ScriptResult checkElementExistM(String values, String type){
        ScriptResult result = new ScriptResult(ScriptMethod.CHECK_ELEMENT_EXIST);
        result.setInvokeMethod(ScriptMethod.CHECK_ELEMENT_EXIST.methodName +"("+values+","+type+")");
        return result;
    }
    private ScriptResult getElementM(String values, String type){
        ScriptResult result = new ScriptResult(ScriptMethod.GET_ELEMENT);
        result.setInvokeMethod(ScriptMethod.GET_ELEMENT.methodName +"("+values+","+type+")");
        return result;
    }

    private ScriptResult deleteElementM(String values, String type){
        ScriptResult result = new ScriptResult(ScriptMethod.DELETE_ELEMENT);
        result.setInvokeMethod(ScriptMethod.DELETE_ELEMENT.methodName +"("+values+","+type+")");
        return result;
    }

    private ScriptResult addOrUpdateVertexM(String primaryKey, String label, String modifyValues, String deleteValues){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_OR_UPDATE_VERTEX);
        result.setInvokeMethod(ScriptMethod.ADD_OR_UPDATE_VERTEX.methodName +"("+primaryKey+","+label+","+modifyValues+","+deleteValues+")");
        return result;
    }

    private ScriptResult addVertexBeforeCheckM(String primaryKey, String label, String values){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_VERTEX_BEFORE_CHECK);
        result.setInvokeMethod(ScriptMethod.ADD_VERTEX_BEFORE_CHECK.methodName +"("+primaryKey+","+label+","+values+")");
        return result;
    }

    private ScriptResult addEdgeM(String source, String target, String values, String label){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_EDGE);
        result.setInvokeMethod(ScriptMethod.ADD_EDGE.methodName +"("+source+","+target+","+values+","+label+")");
        return result;
    }

    private ScriptResult addEdgeBeforeCheckM(String primaryKey, String source, String target, String values, String label){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_EDGE_BEFORE_CHECK);
        result.setInvokeMethod(ScriptMethod.ADD_EDGE_BEFORE_CHECK.methodName +"("+primaryKey+","+source+","+target+","+values+","+label+")");
        return result;
    }

    private ScriptResult addOrUpdateEdgeM(String primaryKey, String source, String target,
                                          String values, String deleteValues, String label){
        ScriptResult result = new ScriptResult(ScriptMethod.ADD_OR_UPDATE_EDGE);
        result.setInvokeMethod(ScriptMethod.ADD_OR_UPDATE_EDGE.methodName +"("+primaryKey+","+source+","+target+","+values+","+deleteValues+","+label+")");
        return result;
    }
    private ScriptResult updateElementM(String primaryKey, String modifyValues, String deleteValues){
        ScriptResult result = new ScriptResult(ScriptMethod.UPDATE_ELEMENT);
        result.setInvokeMethod(ScriptMethod.UPDATE_ELEMENT.methodName +"("+primaryKey+","+modifyValues+","+deleteValues+")");
        return result;
    }

    private ScriptResult dataTransferM(String type, String titanIds, String fieldMap){
        ScriptResult result = new ScriptResult(ScriptMethod.DATA_TRANSFER);
        result.setInvokeMethod(ScriptMethod.DATA_TRANSFER.methodName +"("+type+","+titanIds+","+fieldMap+")");
        return result;
    }


    private enum KeyWords {
        node,edge,source,target,addVertex,has,next,id,addEdge,property,script
    }
    private int firstIndex =0;
    public TitanScriptBuilder(){
        this.script.append(ScriptMethod.SCRIPT_COMMON_METHOD.script);
        this.firstIndex = 0;
    }

    public StringBuilder getScript() {
        return script;
    }

    public void setScript(StringBuilder script) {
        this.script = script;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    private class Variable{
        String variable ="";
        String suffix;
        int secondIndex =0;

        public int secondIndex() {
            secondIndex ++ ;
            return secondIndex;
        }

        public Variable(String variable, String suffix) {
            this.variable = variable;
            this.suffix = suffix;
        }

        public String getSuffix() {
            secondIndex ++ ;
            return suffix +"_" +secondIndex;
        }

        public String getVariable() {
            return variable + getSuffix();
        }
    }

    public class ScriptAndParam{
        public ScriptAndParam(StringBuilder script, Map<String, Object> param){
            this.script = script;
            this.param = param;
        }
        StringBuilder script ;
        Map<String, Object> param;

        public StringBuilder getScript() {
            return script;
        }

        public Map<String, Object> getParam() {
            return param;
        }
    }

    /**
     * public Vertex method0(){addVertexEsp(labelV0_1,paramV0_2) };
     * */
    public  TitanScriptBuilder add(TitanModel model){
        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (titanScriptModel == null){
            return this;
        }
        Variable variable = createVariable(titanScriptModel.getType());

        dealScriptAndParam(add(titanScriptModel, variable),titanScriptModel,false);
        return this;
    }

    /**
     * public Vertex method0(){Vertex ele=getElement(paramV0_1,'vertex');updateElement(ele,paramV0_2);deleteProperties(ele,paramV0_3); }
     * */
    public TitanScriptBuilder update(TitanModel model){
        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (titanScriptModel == null){
            return this;
        }
        Variable variable = createVariable(titanScriptModel.getType());

        dealScriptAndParam(update(titanScriptModel, variable), titanScriptModel,false);
        return this;
    }

    /**
     * public Vertex method1(){
     *  if(!checkElementExist(paramV1_6,paramV1_7)){
     *      addVertexEsp(labelV1_1,paramV1_2);
     *  }else{
     *      Vertex ele=getElement(paramV1_3,'vertex');
     *      updateElement(ele,paramV1_4);
     *      deleteProperties(ele,paramV1_5);
     *  }
     * }
     * */
    public TitanScriptBuilder addOrUpdate(TitanModel model){
        if (model == null){
            return this;
        }

        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Variable variable = createVariable(titanScriptModel.getType());
        dealScriptAndParam(addOrUpdate(titanScriptModel,variable),titanScriptModel,false);
        return this;
    }

    /**
     * 添加前检查数据是否已经存在，如果不存在则添加，否则不进行任何操作，边和点通用
     * */
    public TitanScriptBuilder addBeforeCheckExist(TitanModel model){
        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (titanScriptModel == null){
            return this;
        }
        Variable variable = createVariable(titanScriptModel.getType());

        dealScriptAndParam(addBeforeCheckExist(titanScriptModel, variable), titanScriptModel,false);
        return this;
    }

    /**
     * 获取节点
     * */
    public TitanScriptBuilder get(TitanModel model){
        return this;
    }

    public TitanScriptBuilder delete(TitanModel model){
        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (titanScriptModel == null){
            return this;
        }
        Variable variable = createVariable(titanScriptModel.getType());

        dealScriptAndParam(delete(titanScriptModel, variable), titanScriptModel,false);
        return this;
    }

    public TitanScriptBuilder deleteEdgeById(String identifier){
        Variable variable = createVariable(TitanScriptModel.Type.E);
        HashMap<String,Object> param = new HashMap<>();
        String paramName = variable.getVariable();
        ScriptResult scriptMethod = deleteEdgeByIdentifierM(paramName);
        param.put(paramName,identifier);
        appendScriptMethod(scriptMethod.getScriptMethod());
        this.param.putAll(param);
        this.methodNames.add(scriptMethod.invokeMethod());
        this.firstIndex ++;
        return this;
    }

    /**
     * script:g.V().has('identifier',{0}).has('primary_category',{1}).has('lc_enable',{2})
     *
     * */
    public TitanScriptBuilder script(String script, Object... params){

        Variable variable = createVariable(TitanScriptModel.Type.S);
        Map<String, Object> newParams = new HashMap<>();
        int index = 0;
        while (script.contains("{"+index+"}")){
            String paramName = variable.getVariable();
            script = script.replace("{"+index+"}",paramName);
            newParams.put(paramName, params[index]);
            index ++;
        }

        ScriptAndParam scriptAndParam = new ScriptAndParam(new StringBuilder(script), newParams);
        dealScriptAndParam(scriptAndParam, null,true);
        return this;
    }

    public TitanScriptBuilder patch(TitanModel titanModel, Map<String, Object> patchPropertyMap){
        if (titanModel == null || (patchPropertyMap!=null && patchPropertyMap.size()!=0)){
            return this;
        }
        TitanScriptModel titanScriptModel = null;
        try {
            titanScriptModel = ParseAnnotation.createScriptModel(titanModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Variable variable = createVariable(titanScriptModel.getType());
        dealScriptAndParam(patchElement(titanScriptModel,variable, patchPropertyMap),titanScriptModel,false);
        return this;
    }

    public TitanScriptBuilder modify(Map<String, Object> compositeKey, Map<String, Object> valueMap){
        Variable variable = createVariable(TitanScriptModel.Type.S);

        TitanScriptModel titanScriptModel = new TitanScriptModel();
        titanScriptModel.setCompositeKeyMap(compositeKey);
        titanScriptModel.setFieldMap(valueMap);

        dealScriptAndParam(update(titanScriptModel,variable),titanScriptModel,false);
        return this;

    }

    /**
     * @param type 数据类型 edge或者node
     * @param titanIds titanId
     * @param sourceAndTargetField 原字段名、目标字段名，字段名是titan数据空使用的字段，即@TitanField中定义的字段名
     * */
    public TitanScriptBuilder dataTransfer(String type, List<String> titanIds, Map<String, String> sourceAndTargetField){
        Variable variable = createVariable(TitanScriptModel.Type.S);
        String typeParam = variable.getVariable();
        String titanIdsParam = variable.getVariable();
        String titanFieldMap = variable.getVariable();
        ScriptResult result = dataTransferM(typeParam, titanIdsParam ,titanFieldMap);
        StringBuilder script = new StringBuilder();
        script.append(result.invokeMethod());

        appendScriptMethod(ScriptMethod.DATA_TRANSFER);

        Map<String, Object> param = new HashMap<>();
        param.put(typeParam, type);
        param.put(titanIdsParam, titanIds);
        param.put(titanFieldMap, sourceAndTargetField);

        ScriptAndParam scriptAndParam = new ScriptAndParam(script, param);

        dealScriptAndParam(scriptAndParam, null ,false);

        return this;
    }

    /**
     * 整个脚本结束，组合脚本中的方法，边和点通用
     * */
    public TitanScriptBuilder scriptEnd(){
        for (String methodName : methodNames){
            script.append(methodName).append(";");
        }
        script.append("1+1");
        ScriptMethod.clean();
        return null;
    }


    /**
     * 为脚本添加方法头不合尾部
     * */
    private void dealScriptAndParam(ScriptAndParam scriptAndParam, TitanScriptModel type, boolean addMethod){
        String returnType = null;
        if (type == null){
            returnType = "void";
        }else if (type instanceof TitanScriptModelEdge){
            returnType = "Edge";
        } else if (type instanceof TitanScriptModelVertex){
            returnType = "Vertex";
        }
        if (addMethod){
            String method = "method" + firstIndex +"()";
            methodNames.add(method);
            this.script.append("public ").append(returnType).append(" ").append(method).append("{").append(scriptAndParam.getScript()).append(" ").append("}").append(";");
        } else {
            methodNames.add(scriptAndParam.getScript().toString());
        }
        this.param.putAll(scriptAndParam.getParam());
        this.firstIndex ++ ;
    }

    private ScriptAndParam delete(TitanScriptModel titanScriptModel, Variable variable){
        StringBuilder script = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        String paramName =variable.getVariable();
        param.put(paramName, titanScriptModel.getCompositeKeyMap());
        ScriptResult deleteElement;
        if (titanScriptModel instanceof TitanScriptModelVertex){
            deleteElement = deleteElementM(paramName,"'vertex'");
        } else {
            deleteElement = deleteElementM(paramName,"'edge'");
        }
        script.append(deleteElement.invokeMethod());
        appendScriptMethod(deleteElement.getScriptMethod());
        return new ScriptAndParam(script, param);
    }

    /**
     * @param titanScriptModel titan脚本参数
     * @param variable 单个脚本所需要的一些参数
     * 脚本模板 if(exist){update_script}else{add_script}
     * */
    private ScriptAndParam addOrUpdate(TitanScriptModel titanScriptModel, Variable variable){
        StringBuilder script = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        List<String> deleteProperties = new ArrayList<>();
        Map<String, Object> modifyProperties = new HashMap<>();
        Map<String, Object> fieldMap = titanScriptModel.getFieldMap();
        for (String key : fieldMap.keySet()){
            if (fieldMap.get(key) == null){
                deleteProperties.add(key);
            } else {
                modifyProperties.put(key, fieldMap.get(key));
            }
        }

        String primaryKeyName = variable.getVariable();
        String deletePropertyName = variable.getVariable();
        String modifyPropertyName = variable.getVariable();
        String labelName = variable.getVariable();
        if (titanScriptModel instanceof TitanScriptModelVertex){
            ScriptResult scriptMethod = addOrUpdateVertexM(primaryKeyName,labelName,modifyPropertyName,deletePropertyName);
            script.append(scriptMethod.invokeMethod());
            appendScriptMethod(ScriptMethod.ADD_OR_UPDATE_VERTEX);
            appendScriptMethod(ScriptMethod.ADD_VERTEX_ESP);
            appendScriptMethod(ScriptMethod.UPDATE_PROPERTIES);
            appendScriptMethod(ScriptMethod.DELETE_PROPERTIES);
        } else if (titanScriptModel instanceof TitanScriptModelEdge){
            String sourceName = variable.getVariable();
            String targetName = variable.getVariable();
            ScriptResult scriptMethod = addOrUpdateEdgeM(primaryKeyName,sourceName, targetName,modifyPropertyName,deletePropertyName,labelName);

            param.put(sourceName, ((TitanScriptModelEdge) titanScriptModel).getResourceKeyMap());
            param.put(targetName, ((TitanScriptModelEdge) titanScriptModel).getTargetKeyMap());
            appendScriptMethod(scriptMethod.getScriptMethod());
            appendScriptMethod(ScriptMethod.ADD_OR_UPDATE_EDGE);
            appendScriptMethod(ScriptMethod.ADD_EDGE_ESP);
            appendScriptMethod(ScriptMethod.UPDATE_PROPERTIES);
            appendScriptMethod(ScriptMethod.DELETE_PROPERTIES);
            appendScriptMethod(ScriptMethod.GET_ELEMENT);

            script.append(scriptMethod.invokeMethod());
        }

        param.put(labelName, titanScriptModel.getLabel());
        param.put(primaryKeyName, titanScriptModel.getCompositeKeyMap());
        param.put(deletePropertyName, deleteProperties);
        param.put(modifyPropertyName, modifyProperties);

        return new ScriptAndParam(script, param);
    }

    /**
     * @param titanScriptModel titan脚本参数
     * @param variable 单个脚本所需要的一些参数
     * 脚本模板 if(!exist){add_script}
     * */
    private ScriptAndParam addBeforeCheckExist(TitanScriptModel titanScriptModel,Variable variable){
        StringBuilder script = new StringBuilder();
        Map<String, Object> param = new HashMap<>();

        Map<String, Object> modifyProperties = new HashMap<>();
        Map<String, Object> fieldMap = titanScriptModel.getFieldMap();
        for (String key : fieldMap.keySet()){
            if (fieldMap.get(key) != null){
                modifyProperties.put(key, fieldMap.get(key));
            }
        }

        String primaryKeyName = variable.getVariable();
        String modifyPropertyName = variable.getVariable();
        if (titanScriptModel instanceof TitanScriptModelVertex){
            String labelName = variable.getVariable();
            ScriptResult scriptMethod = addVertexBeforeCheckM(primaryKeyName,labelName,modifyPropertyName);
            param.put(labelName, titanScriptModel.getLabel());

            script.append(scriptMethod.invokeMethod());

            appendScriptMethod(ScriptMethod.ADD_VERTEX_BEFORE_CHECK);
            appendScriptMethod(ScriptMethod.CHECK_ELEMENT_EXIST);
            appendScriptMethod(ScriptMethod.ADD_EDGE_ESP);
            appendScriptMethod(ScriptMethod.ADD_VERTEX_ESP);
        } else if (titanScriptModel instanceof TitanScriptModelEdge){
            String sourceName = variable.getVariable();
            String targetName = variable.getVariable();
            String labelName = variable.getVariable();
            ScriptResult scriptMethod = addEdgeBeforeCheckM(primaryKeyName,sourceName,targetName,modifyPropertyName,labelName);
            param.put(labelName, titanScriptModel.getLabel());
            param.put(sourceName, ((TitanScriptModelEdge) titanScriptModel).getResourceKeyMap());
            param.put(targetName, ((TitanScriptModelEdge) titanScriptModel).getTargetKeyMap());

            script.append(scriptMethod.invokeMethod());

            appendScriptMethod(ScriptMethod.ADD_EDGE_BEFORE_CHECK);
            appendScriptMethod(ScriptMethod.ADD_EDGE);
            appendScriptMethod(ScriptMethod.CHECK_ELEMENT_EXIST);
            appendScriptMethod(ScriptMethod.GET_ELEMENT);
        }

        param.put(primaryKeyName, titanScriptModel.getCompositeKeyMap());
        param.put(modifyPropertyName, modifyProperties);
        return new ScriptAndParam(script, param);
    }

    /**
     * @param titanScriptModel titan脚本参数
     * @param variable 单个脚本所需要的一些参数

     * 脚本模板 add_script : node_0 = graph.addVertex(T.label,label,pro1,value1,...).id()
     * */
    private ScriptAndParam add(TitanScriptModel titanScriptModel,Variable variable){

        StringBuilder script = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        if (titanScriptModel instanceof TitanScriptModelVertex){
            addVertex(titanScriptModel.getFieldMap(),titanScriptModel.getLabel(),variable,script,param);
        }
        if (titanScriptModel instanceof TitanScriptModelEdge){
            TitanScriptModelEdge edge = (TitanScriptModelEdge) titanScriptModel;
            addEdge(edge,variable, script, param);
        }

        return new ScriptAndParam(script, param);
    }

    /**
     * @param titanScriptModel titan脚本参数
     * @param variable 单个脚本所需要的一些参数
     * 脚本模板 update_script:g.V().has('pro1',value1)...property('pro2',value2)...properties('pro3','pro4').drop()
     * */
    private ScriptAndParam update(TitanScriptModel titanScriptModel,Variable variable){
        //TODO 缺少对null属性的删除
        StringBuilder script = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        //分离需要更新和需要删除的属性
        Map<String, Object> updateValues = new HashMap<>();
        List<String> dropValues = new ArrayList<>();
        for (String key : titanScriptModel.getFieldMap().keySet()){
            Object obj = titanScriptModel.getFieldMap().get(key);
            if (obj == null){
                dropValues.add(key);
            } else {
                updateValues.put(key, obj);
            }
        }

        String primaryKey = variable.getVariable();
        String updateValueName = variable.getVariable();
        String dropValueName = variable.getVariable();

        ScriptResult updateMethod = updateElementM(primaryKey,updateValueName,dropValueName);
        appendScriptMethod(ScriptMethod.UPDATE_ELEMENT);
        appendScriptMethod(ScriptMethod.UPDATE_PROPERTIES);
        appendScriptMethod(ScriptMethod.DELETE_PROPERTIES);

        param.put(primaryKey,titanScriptModel.getCompositeKeyMap());
        param.put(updateValueName, updateValues);
        param.put(dropValueName, dropValues);

        script.append(updateMethod.invokeMethod());
        return new ScriptAndParam(script, param);
    }

    private ScriptAndParam patchElement(TitanScriptModel titanScriptModel,Variable variable, Map<String, Object> patchPropertyMap){
        TitanScriptModel updateTitanScriptModel = new TitanScriptModel();
        updateTitanScriptModel.setCompositeKeyMap(titanScriptModel.getCompositeKeyMap());
        updateTitanScriptModel.setLabel(titanScriptModel.getLabel());
        updateTitanScriptModel.setType(titanScriptModel.getType());
        updateTitanScriptModel.setFieldNameAndTitanNameMap(titanScriptModel.getFieldNameAndTitanNameMap());

        Map<String, Object> fieldMap = new HashMap<>();
        for (String key : patchPropertyMap.keySet()){
            String titanKey = titanScriptModel.getFieldNameAndTitanNameMap().get(key);
            if (titanKey == null){
                continue;
            }
            fieldMap.put(titanKey, patchPropertyMap.get(key));
        }

        updateTitanScriptModel.setFieldMap(fieldMap);
        return update(updateTitanScriptModel, variable);
    }

    private void addVertex(Map<String, Object> values, String label , Variable variable, StringBuilder script , Map<String, Object> param){
        String labelName = variable.getVariable();
        String paramName = variable.getVariable();

        Map<String, Object> newValues = new HashMap<>();
        for (String key : values.keySet()){
            if (values.get(key)!=null){
                newValues.put(key, values.get(key));
            }
        }

        param.put(labelName, label);
        param.put(paramName, newValues);

        ScriptResult addVertex = addVertexEspM(labelName, paramName);
        script.append(addVertex.invokeMethod);
        appendScriptMethod(addVertex.getScriptMethod());
    }

    private void addEdge(TitanScriptModelEdge edge, Variable variable, StringBuilder script , Map<String, Object> param){
        //筛选除需要添加的属性
        Map<String, Object> newParam = new HashMap<>();
        for (String key : edge.getFieldMap().keySet()){
            if (edge.getFieldMap().get(key)==null){
                continue;
            }
            newParam.put(key, edge.getFieldMap().get(key));
        }
        String sourceName = variable.getVariable();
        String targetName = variable.getVariable();
        String labelName = variable.getVariable();
        String valuesName = variable.getVariable();
        param.put(sourceName, edge.getResourceKeyMap());
        param.put(targetName, edge.getTargetKeyMap());
        param.put(labelName, edge.getLabel());
        param.put(valuesName, newParam);
        ScriptResult method = addEdgeM(sourceName, targetName, valuesName, labelName);

        script.append(method.invokeMethod());
        appendScriptMethod(ScriptMethod.ADD_EDGE);
        appendScriptMethod(ScriptMethod.ADD_EDGE_ESP);
        appendScriptMethod(ScriptMethod.GET_ELEMENT);
    }

    private Variable createVariable(TitanScriptModel.Type type){
        Variable variable = null;
        String suffix = ""+firstIndex;
        if (type.equals(TitanScriptModel.Type.E)){
           variable = new Variable(KeyWords.edge.toString(), suffix);
        }
        if (type.equals(TitanScriptModel.Type.V)){
            variable = new Variable(KeyWords.node.toString(), suffix);
        }
        if (type.equals(TitanScriptModel.Type.S)){
            variable = new Variable(KeyWords.script.toString(), suffix);
        }

        return variable;
    }

    private void appendScriptMethod(ScriptMethod method){
        if (!scriptMethodSet.contains(method)){
            scriptMethodSet.add(method);
            script.append(method.script);
        }
    }

}
