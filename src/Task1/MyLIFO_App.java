package Task1;

import java.util.LinkedList;
import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> st = new Stack<>();
		for (E e : array) {
			st.push(e);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = st.pop();
		}
	}

	// This method checks the correctness of the given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		Stack<Character> stack = new Stack<>();
		for (Character ch : input.toCharArray()) {
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			} else if (ch == ')' || ch == ']' || ch == '}') {
				if (stack.isEmpty()) {
					return false;
				}
				if (ch == ')' && stack.pop() != '(' || ch == ']' && stack.pop() != '['
						|| ch == '}' && stack.pop() != '{') {
					return false;
				}

			}

		}
		return stack.isEmpty();
	}

	// This method evaluates the value of an expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String expression) {
	    char[] tokens = expression.toCharArray();

	    Stack<Integer> operandStack = new Stack<>();
	    Stack<Character> operatorStack = new Stack<>();

	    for (int i = 0; i < tokens.length; i++) {
	        if (tokens[i] == ' ') {
	            continue;
	        }

	        if (Character.isDigit(tokens[i])) {
	            StringBuilder sbuf = new StringBuilder();

	            while (i < tokens.length && Character.isDigit(tokens[i])) {
	                sbuf.append(tokens[i++]);
	            }
	            operandStack.push(Integer.parseInt(sbuf.toString()));
	            i--;
	        } else if (tokens[i] == '(') {
	            operatorStack.push(tokens[i]);
	        }else if (tokens[i] == ')') {
	            while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
	                operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
	            }
	            if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
	                operatorStack.pop();
	            }
	        } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
	            while (!operatorStack.isEmpty() && processOperators(tokens[i], operatorStack.peek())) {
	                operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
	            }
	            operatorStack.push(tokens[i]);
	        }
	    }

	    while (!operatorStack.isEmpty()) {
	        operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
	    }

	    return operandStack.pop();
	}

	public static boolean processOperators(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	private static int performOperation(char operator, int op1, int op2) {
		int result = 0;
		if (operator == '+') {
			result = op1 + op2;
		} else if (operator == '-') {
			result = op1 - op2;
		} else if (operator == '*') {
			result = op1 * op2;
		} else if (operator == '/') {
			if (op2 == 0) {
				throw new ArithmeticException("khong chia duoc cho 0");
			}
			result = op1 / op2;
		}
		return result;
	}

	public static <E> void printArray(E[] array) {
		for (E element : array) {
			System.out.print(element + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		Integer[] intArray = { 1, 2, 3, 4, 5 };
//		reserve(intArray);
//		System.out.println("Reversed Array:");
//		printArray(intArray);
//
//		String st = "[]";
//		System.out.println(isCorrect(st));
		String st1 = "51+(54*(3+2)))";
		System.out.println("ketqua = " + evaluateExpression(st1));
	}


}
