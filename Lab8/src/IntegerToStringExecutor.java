public class IntegerToStringExecutor implements IExecutor<Integer, String> {
    StringBuffer line = new StringBuffer();
    @Override
    public void execute(Integer elem) {
        line.append(elem + "; ");
    }
    @Override
    public String getResult() {
        line.delete(line.length() - 2, line.length());
        String ret =  line.toString();
        line.setLength(0);
        return ret;
    }
}
