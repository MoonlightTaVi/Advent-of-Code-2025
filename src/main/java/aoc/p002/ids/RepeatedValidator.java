package aoc.p002.ids;


public class RepeatedValidator implements IdValidatorIF {

    @Override
    public boolean validate(String id) {
        boolean isInvalid = false;
        int divisor = 2;
        int length = id.length();
        while (length / divisor >= 1) {
            String part = getSubpart(id, divisor);
            if (isRepeated(id, part)) {
                isInvalid = true;
                break;
            }
            divisor++;
        }
        return !isInvalid;
    }
    
    public String getSubpart(String id, int divisor) {
        int partLength = id.length() / divisor;
        return id.substring(0, partLength);
    }
    
    public boolean isRepeated(String fullId, String idPart) {
        int fullLength = fullId.length();
        int partLength = idPart.length();
        int divisor = fullLength / partLength;
        boolean result = checkDivision(fullLength, divisor, partLength);
        if (result) {
            for (int i = 0; i < fullLength; i += partLength) {
                String part = fullId.substring(i, i + partLength);
                if (!part.equals(idPart)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    
    private boolean checkDivision(
            int dividend, 
            int divisor, 
            int qoutient
            ) {
        return divisor * qoutient == dividend;
    }
}
