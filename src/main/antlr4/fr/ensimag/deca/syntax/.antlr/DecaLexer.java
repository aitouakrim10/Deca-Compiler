// Generated from /home/aitouaka/ensimag/GL/gl31/src/main/antlr4/fr/ensimag/deca/syntax/DecaLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DecaLexer extends AbstractDecaLexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OBRACE=1, CBRACE=2, OPARENT=3, CPARENT=4, SEMI=5, LBRACKET=6, RBRACKET=7, 
		UNDER=8, COMMA=9, EQUALS=10, PLUS=11, MINUS=12, TIMES=13, AND=14, OR=15, 
		WHILE=16, RETURN=17, EQEQ=18, NEQ=19, LEQ=20, GEQ=21, GT=22, LT=23, COMMENT=24, 
		COMMENTLINE=25, STRING=26, MULTI_LINE_STRING=27, PERCENT=28, EXCLAM=29, 
		SLASH=30, NEW=31, DOT=32, PRINTLN=33, PRINT=34, PRINTX=35, PRINTLNX=36, 
		IF=37, ELSE=38, TRUE=39, FALSE=40, THIS=41, CLASS=42, EXTENDS=43, PROTECTED=44, 
		NULL=45, INSTANCEOF=46, READINT=47, READFLOAT=48, ASM=49, INCLUDE=50, 
		FLOAT=51, INT=52, IDENT=53, EOL=54, TAB=55, Br=56, ESPACE=57, VIDE=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SPE", "STRING_CAR", "CHAR", "DIGIT", "POSTIVE_DIGIT", "NUM", "SIGN", 
			"EXP", "DEC", "FlOATDEC", "DIGITHEX", "NUMHEX", "FLOATHEX", "OBRACE", 
			"CBRACE", "OPARENT", "CPARENT", "SEMI", "LBRACKET", "RBRACKET", "UNDER", 
			"COMMA", "EQUALS", "PLUS", "MINUS", "TIMES", "AND", "OR", "WHILE", "RETURN", 
			"EQEQ", "NEQ", "LEQ", "GEQ", "GT", "LT", "COMMENT", "COMMENTLINE", "STRING", 
			"MULTI_LINE_STRING", "PERCENT", "EXCLAM", "SLASH", "NEW", "DOT", "PRINTLN", 
			"PRINT", "PRINTX", "PRINTLNX", "IF", "ELSE", "TRUE", "FALSE", "THIS", 
			"CLASS", "EXTENDS", "PROTECTED", "NULL", "INSTANCEOF", "READINT", "READFLOAT", 
			"ASM", "FILENAME", "INCLUDE", "FLOAT", "INT", "IDENT", "EOL", "TAB", 
			"Br", "ESPACE", "VIDE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "';'", "'['", "']'", "'_'", "','", 
			"'='", "'+'", "'-'", "'*'", "'&&'", "'||'", "'while'", "'return'", "'=='", 
			"'!='", "'<='", "'>='", "'>'", "'<'", null, null, null, null, "'%'", 
			"'!'", "'/'", "'new'", "'.'", "'println'", "'print'", "'printx'", "'printlnx'", 
			"'if'", "'else'", "'true'", "'false'", "'this'", "'class'", "'extends'", 
			"'protected'", "'null'", "'instanceof'", "'readInt'", "'readFloat'", 
			"'asm'", null, null, null, null, null, "'\\t'", "'\\r'", "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OBRACE", "CBRACE", "OPARENT", "CPARENT", "SEMI", "LBRACKET", "RBRACKET", 
			"UNDER", "COMMA", "EQUALS", "PLUS", "MINUS", "TIMES", "AND", "OR", "WHILE", 
			"RETURN", "EQEQ", "NEQ", "LEQ", "GEQ", "GT", "LT", "COMMENT", "COMMENTLINE", 
			"STRING", "MULTI_LINE_STRING", "PERCENT", "EXCLAM", "SLASH", "NEW", "DOT", 
			"PRINTLN", "PRINT", "PRINTX", "PRINTLNX", "IF", "ELSE", "TRUE", "FALSE", 
			"THIS", "CLASS", "EXTENDS", "PROTECTED", "NULL", "INSTANCEOF", "READINT", 
			"READFLOAT", "ASM", "INCLUDE", "FLOAT", "INT", "IDENT", "EOL", "TAB", 
			"Br", "ESPACE", "VIDE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}




	public DecaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DecaLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 36:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 37:
			COMMENTLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			INCLUDE_action((RuleContext)_localctx, actionIndex);
			break;
		case 67:
			EOL_action((RuleContext)_localctx, actionIndex);
			break;
		case 68:
			TAB_action((RuleContext)_localctx, actionIndex);
			break;
		case 69:
			Br_action((RuleContext)_localctx, actionIndex);
			break;
		case 70:
			ESPACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 71:
			VIDE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 skip(); 
			break;
		}
	}
	private void COMMENTLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 skip(); 
			break;
		}
	}
	private void INCLUDE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 doInclude(getText());
			break;
		}
	}
	private void EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			skip();
			break;
		}
	}
	private void TAB_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			skip();
			break;
		}
	}
	private void Br_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			skip();
			break;
		}
	}
	private void ESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			skip();
			break;
		}
	}
	private void VIDE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			 skip(); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000:\u0212\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007"+
		"D\u0002E\u0007E\u0002F\u0007F\u0002G\u0007G\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0004\u0005\u009d\b\u0005\u000b\u0005\f"+
		"\u0005\u009e\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00a4\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00b2\b\t\u0001\t\u0001"+
		"\t\u0003\t\u00b6\b\t\u0001\n\u0001\n\u0001\u000b\u0004\u000b\u00bb\b\u000b"+
		"\u000b\u000b\f\u000b\u00bc\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00c3"+
		"\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00cd\b\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001$\u0001"+
		"$\u0005$\u0110\b$\n$\f$\u0113\t$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"%\u0001%\u0001%\u0001%\u0005%\u011e\b%\n%\f%\u0121\t%\u0001%\u0001%\u0003"+
		"%\u0125\b%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0005"+
		"&\u012f\b&\n&\f&\u0132\t&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0004\'\u013d\b\'\u000b\'\f\'\u013e\u0005\'"+
		"\u0141\b\'\n\'\f\'\u0144\t\'\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001,\u0001,\u0001-\u0001"+
		"-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001"+
		"0\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00011\u0001"+
		"1\u00011\u00012\u00012\u00012\u00012\u00012\u00013\u00013\u00013\u0001"+
		"3\u00013\u00014\u00014\u00014\u00014\u00014\u00014\u00015\u00015\u0001"+
		"5\u00015\u00015\u00016\u00016\u00016\u00016\u00016\u00016\u00017\u0001"+
		"7\u00017\u00017\u00017\u00017\u00017\u00017\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00018\u00019\u00019\u00019\u0001"+
		"9\u00019\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001=\u0001=\u0001=\u0001=\u0001>\u0001>\u0001>\u0004>\u01cb\b>\u000b"+
		">\f>\u01cc\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0005?\u01d9\b?\n?\f?\u01dc\t?\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001@\u0001@\u0003@\u01e5\b@\u0001A\u0001A\u0004A\u01e9\bA\u000bA\f"+
		"A\u01ea\u0001B\u0001B\u0004B\u01ef\bB\u000bB\fB\u01f0\u0001B\u0001B\u0001"+
		"B\u0005B\u01f6\bB\nB\fB\u01f9\tB\u0001C\u0004C\u01fc\bC\u000bC\fC\u01fd"+
		"\u0001C\u0001C\u0001D\u0001D\u0001D\u0001E\u0001E\u0001E\u0001F\u0001"+
		"F\u0001F\u0001G\u0001G\u0004G\u020d\bG\u000bG\fG\u020e\u0001G\u0001G\u0002"+
		"\u0111\u011f\u0000H\u0001\u0000\u0003\u0000\u0005\u0000\u0007\u0000\t"+
		"\u0000\u000b\u0000\r\u0000\u000f\u0000\u0011\u0000\u0013\u0000\u0015\u0000"+
		"\u0017\u0000\u0019\u0000\u001b\u0001\u001d\u0002\u001f\u0003!\u0004#\u0005"+
		"%\u0006\'\u0007)\b+\t-\n/\u000b1\f3\r5\u000e7\u000f9\u0010;\u0011=\u0012"+
		"?\u0013A\u0014C\u0015E\u0016G\u0017I\u0018K\u0019M\u001aO\u001bQ\u001c"+
		"S\u001dU\u001eW\u001fY [!]\"_#a$c%e&g\'i(k)m*o+q,s-u.w/y0{1}\u0000\u007f"+
		"2\u00813\u00834\u00855\u00876\u00897\u008b8\u008d9\u008f:\u0001\u0000"+
		"\n\b\u0000!!#&*,./:@^^{{}}\u0003\u0000\n\n\"\"\\\\\u0002\u0000AZaz\u0002"+
		"\u0000EEee\u0002\u0000FFff\u0003\u000009AFaf\u0002\u0000PPpp\u0001\u0000"+
		"\n\n\u0002\u0000-.__\u0002\u0000$$__\u0226\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000"+
		"\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000"+
		"A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001"+
		"\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000"+
		"\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000"+
		"O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001"+
		"\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000"+
		"\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000"+
		"]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001"+
		"\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000"+
		"\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000"+
		"k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001"+
		"\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000\u0000s\u0001\u0000\u0000"+
		"\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w\u0001\u0000\u0000\u0000\u0000"+
		"y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000\u0000\u0000\u0000\u007f\u0001"+
		"\u0000\u0000\u0000\u0000\u0081\u0001\u0000\u0000\u0000\u0000\u0083\u0001"+
		"\u0000\u0000\u0000\u0000\u0085\u0001\u0000\u0000\u0000\u0000\u0087\u0001"+
		"\u0000\u0000\u0000\u0000\u0089\u0001\u0000\u0000\u0000\u0000\u008b\u0001"+
		"\u0000\u0000\u0000\u0000\u008d\u0001\u0000\u0000\u0000\u0000\u008f\u0001"+
		"\u0000\u0000\u0000\u0001\u0091\u0001\u0000\u0000\u0000\u0003\u0093\u0001"+
		"\u0000\u0000\u0000\u0005\u0095\u0001\u0000\u0000\u0000\u0007\u0097\u0001"+
		"\u0000\u0000\u0000\t\u0099\u0001\u0000\u0000\u0000\u000b\u009c\u0001\u0000"+
		"\u0000\u0000\r\u00a3\u0001\u0000\u0000\u0000\u000f\u00a5\u0001\u0000\u0000"+
		"\u0000\u0011\u00a9\u0001\u0000\u0000\u0000\u0013\u00b1\u0001\u0000\u0000"+
		"\u0000\u0015\u00b7\u0001\u0000\u0000\u0000\u0017\u00ba\u0001\u0000\u0000"+
		"\u0000\u0019\u00c2\u0001\u0000\u0000\u0000\u001b\u00ce\u0001\u0000\u0000"+
		"\u0000\u001d\u00d0\u0001\u0000\u0000\u0000\u001f\u00d2\u0001\u0000\u0000"+
		"\u0000!\u00d4\u0001\u0000\u0000\u0000#\u00d6\u0001\u0000\u0000\u0000%"+
		"\u00d8\u0001\u0000\u0000\u0000\'\u00da\u0001\u0000\u0000\u0000)\u00dc"+
		"\u0001\u0000\u0000\u0000+\u00de\u0001\u0000\u0000\u0000-\u00e0\u0001\u0000"+
		"\u0000\u0000/\u00e2\u0001\u0000\u0000\u00001\u00e4\u0001\u0000\u0000\u0000"+
		"3\u00e6\u0001\u0000\u0000\u00005\u00e8\u0001\u0000\u0000\u00007\u00eb"+
		"\u0001\u0000\u0000\u00009\u00ee\u0001\u0000\u0000\u0000;\u00f4\u0001\u0000"+
		"\u0000\u0000=\u00fb\u0001\u0000\u0000\u0000?\u00fe\u0001\u0000\u0000\u0000"+
		"A\u0101\u0001\u0000\u0000\u0000C\u0104\u0001\u0000\u0000\u0000E\u0107"+
		"\u0001\u0000\u0000\u0000G\u0109\u0001\u0000\u0000\u0000I\u010b\u0001\u0000"+
		"\u0000\u0000K\u0119\u0001\u0000\u0000\u0000M\u0128\u0001\u0000\u0000\u0000"+
		"O\u0135\u0001\u0000\u0000\u0000Q\u0147\u0001\u0000\u0000\u0000S\u0149"+
		"\u0001\u0000\u0000\u0000U\u014b\u0001\u0000\u0000\u0000W\u014d\u0001\u0000"+
		"\u0000\u0000Y\u0151\u0001\u0000\u0000\u0000[\u0153\u0001\u0000\u0000\u0000"+
		"]\u015b\u0001\u0000\u0000\u0000_\u0161\u0001\u0000\u0000\u0000a\u0168"+
		"\u0001\u0000\u0000\u0000c\u0171\u0001\u0000\u0000\u0000e\u0174\u0001\u0000"+
		"\u0000\u0000g\u0179\u0001\u0000\u0000\u0000i\u017e\u0001\u0000\u0000\u0000"+
		"k\u0184\u0001\u0000\u0000\u0000m\u0189\u0001\u0000\u0000\u0000o\u018f"+
		"\u0001\u0000\u0000\u0000q\u0197\u0001\u0000\u0000\u0000s\u01a1\u0001\u0000"+
		"\u0000\u0000u\u01a6\u0001\u0000\u0000\u0000w\u01b1\u0001\u0000\u0000\u0000"+
		"y\u01b9\u0001\u0000\u0000\u0000{\u01c3\u0001\u0000\u0000\u0000}\u01ca"+
		"\u0001\u0000\u0000\u0000\u007f\u01ce\u0001\u0000\u0000\u0000\u0081\u01e4"+
		"\u0001\u0000\u0000\u0000\u0083\u01e8\u0001\u0000\u0000\u0000\u0085\u01ee"+
		"\u0001\u0000\u0000\u0000\u0087\u01fb\u0001\u0000\u0000\u0000\u0089\u0201"+
		"\u0001\u0000\u0000\u0000\u008b\u0204\u0001\u0000\u0000\u0000\u008d\u0207"+
		"\u0001\u0000\u0000\u0000\u008f\u020c\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0007\u0000\u0000\u0000\u0092\u0002\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\b\u0001\u0000\u0000\u0094\u0004\u0001\u0000\u0000\u0000\u0095\u0096\u0007"+
		"\u0002\u0000\u0000\u0096\u0006\u0001\u0000\u0000\u0000\u0097\u0098\u0002"+
		"09\u0000\u0098\b\u0001\u0000\u0000\u0000\u0099\u009a\u000219\u0000\u009a"+
		"\n\u0001\u0000\u0000\u0000\u009b\u009d\u0003\u0007\u0003\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\f\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a4\u0003/\u0017\u0000\u00a1\u00a4\u00031\u0018"+
		"\u0000\u00a2\u00a4\u0001\u0000\u0000\u0000\u00a3\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a4\u000e\u0001\u0000\u0000\u0000\u00a5\u00a6\u0007\u0003\u0000"+
		"\u0000\u00a6\u00a7\u0003\r\u0006\u0000\u00a7\u00a8\u0003\u0083A\u0000"+
		"\u00a8\u0010\u0001\u0000\u0000\u0000\u00a9\u00aa\u0003\u000b\u0005\u0000"+
		"\u00aa\u00ab\u0005.\u0000\u0000\u00ab\u00ac\u0003\u000b\u0005\u0000\u00ac"+
		"\u0012\u0001\u0000\u0000\u0000\u00ad\u00b2\u0003\u0011\b\u0000\u00ae\u00af"+
		"\u0003\u0011\b\u0000\u00af\u00b0\u0003\u000f\u0007\u0000\u00b0\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b1\u00ad\u0001\u0000\u0000\u0000\u00b1\u00ae\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b6\u0007"+
		"\u0004\u0000\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u0014\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0007\u0005\u0000\u0000\u00b8\u0016\u0001"+
		"\u0000\u0000\u0000\u00b9\u00bb\u0003\u0015\n\u0000\u00ba\u00b9\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u0018\u0001\u0000"+
		"\u0000\u0000\u00be\u00bf\u00050\u0000\u0000\u00bf\u00c3\u0005x\u0000\u0000"+
		"\u00c0\u00c1\u00050\u0000\u0000\u00c1\u00c3\u0005X\u0000\u0000\u00c2\u00be"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0003\u0017\u000b\u0000\u00c5\u00c6"+
		"\u0005.\u0000\u0000\u00c6\u00c7\u0003\u0017\u000b\u0000\u00c7\u00c8\u0007"+
		"\u0006\u0000\u0000\u00c8\u00c9\u0003\r\u0006\u0000\u00c9\u00cc\u0003\u000b"+
		"\u0005\u0000\u00ca\u00cd\u0007\u0004\u0000\u0000\u00cb\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u001a\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0005{\u0000\u0000\u00cf\u001c\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d1\u0005}\u0000\u0000\u00d1\u001e\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0005(\u0000\u0000\u00d3 \u0001\u0000\u0000\u0000\u00d4\u00d5"+
		"\u0005)\u0000\u0000\u00d5\"\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005"+
		";\u0000\u0000\u00d7$\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005[\u0000"+
		"\u0000\u00d9&\u0001\u0000\u0000\u0000\u00da\u00db\u0005]\u0000\u0000\u00db"+
		"(\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005_\u0000\u0000\u00dd*\u0001"+
		"\u0000\u0000\u0000\u00de\u00df\u0005,\u0000\u0000\u00df,\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0005=\u0000\u0000\u00e1.\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e3\u0005+\u0000\u0000\u00e30\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005"+
		"-\u0000\u0000\u00e52\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005*\u0000"+
		"\u0000\u00e74\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005&\u0000\u0000\u00e9"+
		"\u00ea\u0005&\u0000\u0000\u00ea6\u0001\u0000\u0000\u0000\u00eb\u00ec\u0005"+
		"|\u0000\u0000\u00ec\u00ed\u0005|\u0000\u0000\u00ed8\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0005w\u0000\u0000\u00ef\u00f0\u0005h\u0000\u0000\u00f0"+
		"\u00f1\u0005i\u0000\u0000\u00f1\u00f2\u0005l\u0000\u0000\u00f2\u00f3\u0005"+
		"e\u0000\u0000\u00f3:\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005r\u0000"+
		"\u0000\u00f5\u00f6\u0005e\u0000\u0000\u00f6\u00f7\u0005t\u0000\u0000\u00f7"+
		"\u00f8\u0005u\u0000\u0000\u00f8\u00f9\u0005r\u0000\u0000\u00f9\u00fa\u0005"+
		"n\u0000\u0000\u00fa<\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005=\u0000"+
		"\u0000\u00fc\u00fd\u0005=\u0000\u0000\u00fd>\u0001\u0000\u0000\u0000\u00fe"+
		"\u00ff\u0005!\u0000\u0000\u00ff\u0100\u0005=\u0000\u0000\u0100@\u0001"+
		"\u0000\u0000\u0000\u0101\u0102\u0005<\u0000\u0000\u0102\u0103\u0005=\u0000"+
		"\u0000\u0103B\u0001\u0000\u0000\u0000\u0104\u0105\u0005>\u0000\u0000\u0105"+
		"\u0106\u0005=\u0000\u0000\u0106D\u0001\u0000\u0000\u0000\u0107\u0108\u0005"+
		">\u0000\u0000\u0108F\u0001\u0000\u0000\u0000\u0109\u010a\u0005<\u0000"+
		"\u0000\u010aH\u0001\u0000\u0000\u0000\u010b\u010c\u0005/\u0000\u0000\u010c"+
		"\u010d\u0005*\u0000\u0000\u010d\u0111\u0001\u0000\u0000\u0000\u010e\u0110"+
		"\t\u0000\u0000\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u0110\u0113\u0001"+
		"\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0111\u010f\u0001"+
		"\u0000\u0000\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u0111\u0001"+
		"\u0000\u0000\u0000\u0114\u0115\u0005*\u0000\u0000\u0115\u0116\u0005/\u0000"+
		"\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0006$\u0000\u0000"+
		"\u0118J\u0001\u0000\u0000\u0000\u0119\u011a\u0005/\u0000\u0000\u011a\u011b"+
		"\u0005/\u0000\u0000\u011b\u011f\u0001\u0000\u0000\u0000\u011c\u011e\b"+
		"\u0007\u0000\u0000\u011d\u011c\u0001\u0000\u0000\u0000\u011e\u0121\u0001"+
		"\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u011f\u011d\u0001"+
		"\u0000\u0000\u0000\u0120\u0124\u0001\u0000\u0000\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0122\u0125\u0005\n\u0000\u0000\u0123\u0125\u0003\u0087"+
		"C\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0123\u0001\u0000\u0000"+
		"\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0006%\u0001\u0000"+
		"\u0127L\u0001\u0000\u0000\u0000\u0128\u0130\u0005\"\u0000\u0000\u0129"+
		"\u012f\u0003\u0003\u0001\u0000\u012a\u012b\u0005\\\u0000\u0000\u012b\u012f"+
		"\u0005\"\u0000\u0000\u012c\u012d\u0005\\\u0000\u0000\u012d\u012f\u0005"+
		"\\\u0000\u0000\u012e\u0129\u0001\u0000\u0000\u0000\u012e\u012a\u0001\u0000"+
		"\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012f\u0132\u0001\u0000"+
		"\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000"+
		"\u0000\u0000\u0131\u0133\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000"+
		"\u0000\u0000\u0133\u0134\u0005\"\u0000\u0000\u0134N\u0001\u0000\u0000"+
		"\u0000\u0135\u0142\u0005\"\u0000\u0000\u0136\u013d\u0003\u0003\u0001\u0000"+
		"\u0137\u013d\u0005\n\u0000\u0000\u0138\u0139\u0005\\\u0000\u0000\u0139"+
		"\u013d\u0005\"\u0000\u0000\u013a\u013b\u0005\\\u0000\u0000\u013b\u013d"+
		"\u0005\\\u0000\u0000\u013c\u0136\u0001\u0000\u0000\u0000\u013c\u0137\u0001"+
		"\u0000\u0000\u0000\u013c\u0138\u0001\u0000\u0000\u0000\u013c\u013a\u0001"+
		"\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013c\u0001"+
		"\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0141\u0001"+
		"\u0000\u0000\u0000\u0140\u013c\u0001\u0000\u0000\u0000\u0141\u0144\u0001"+
		"\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143\u0001"+
		"\u0000\u0000\u0000\u0143\u0145\u0001\u0000\u0000\u0000\u0144\u0142\u0001"+
		"\u0000\u0000\u0000\u0145\u0146\u0005\"\u0000\u0000\u0146P\u0001\u0000"+
		"\u0000\u0000\u0147\u0148\u0005%\u0000\u0000\u0148R\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0005!\u0000\u0000\u014aT\u0001\u0000\u0000\u0000\u014b\u014c"+
		"\u0005/\u0000\u0000\u014cV\u0001\u0000\u0000\u0000\u014d\u014e\u0005n"+
		"\u0000\u0000\u014e\u014f\u0005e\u0000\u0000\u014f\u0150\u0005w\u0000\u0000"+
		"\u0150X\u0001\u0000\u0000\u0000\u0151\u0152\u0005.\u0000\u0000\u0152Z"+
		"\u0001\u0000\u0000\u0000\u0153\u0154\u0005p\u0000\u0000\u0154\u0155\u0005"+
		"r\u0000\u0000\u0155\u0156\u0005i\u0000\u0000\u0156\u0157\u0005n\u0000"+
		"\u0000\u0157\u0158\u0005t\u0000\u0000\u0158\u0159\u0005l\u0000\u0000\u0159"+
		"\u015a\u0005n\u0000\u0000\u015a\\\u0001\u0000\u0000\u0000\u015b\u015c"+
		"\u0005p\u0000\u0000\u015c\u015d\u0005r\u0000\u0000\u015d\u015e\u0005i"+
		"\u0000\u0000\u015e\u015f\u0005n\u0000\u0000\u015f\u0160\u0005t\u0000\u0000"+
		"\u0160^\u0001\u0000\u0000\u0000\u0161\u0162\u0005p\u0000\u0000\u0162\u0163"+
		"\u0005r\u0000\u0000\u0163\u0164\u0005i\u0000\u0000\u0164\u0165\u0005n"+
		"\u0000\u0000\u0165\u0166\u0005t\u0000\u0000\u0166\u0167\u0005x\u0000\u0000"+
		"\u0167`\u0001\u0000\u0000\u0000\u0168\u0169\u0005p\u0000\u0000\u0169\u016a"+
		"\u0005r\u0000\u0000\u016a\u016b\u0005i\u0000\u0000\u016b\u016c\u0005n"+
		"\u0000\u0000\u016c\u016d\u0005t\u0000\u0000\u016d\u016e\u0005l\u0000\u0000"+
		"\u016e\u016f\u0005n\u0000\u0000\u016f\u0170\u0005x\u0000\u0000\u0170b"+
		"\u0001\u0000\u0000\u0000\u0171\u0172\u0005i\u0000\u0000\u0172\u0173\u0005"+
		"f\u0000\u0000\u0173d\u0001\u0000\u0000\u0000\u0174\u0175\u0005e\u0000"+
		"\u0000\u0175\u0176\u0005l\u0000\u0000\u0176\u0177\u0005s\u0000\u0000\u0177"+
		"\u0178\u0005e\u0000\u0000\u0178f\u0001\u0000\u0000\u0000\u0179\u017a\u0005"+
		"t\u0000\u0000\u017a\u017b\u0005r\u0000\u0000\u017b\u017c\u0005u\u0000"+
		"\u0000\u017c\u017d\u0005e\u0000\u0000\u017dh\u0001\u0000\u0000\u0000\u017e"+
		"\u017f\u0005f\u0000\u0000\u017f\u0180\u0005a\u0000\u0000\u0180\u0181\u0005"+
		"l\u0000\u0000\u0181\u0182\u0005s\u0000\u0000\u0182\u0183\u0005e\u0000"+
		"\u0000\u0183j\u0001\u0000\u0000\u0000\u0184\u0185\u0005t\u0000\u0000\u0185"+
		"\u0186\u0005h\u0000\u0000\u0186\u0187\u0005i\u0000\u0000\u0187\u0188\u0005"+
		"s\u0000\u0000\u0188l\u0001\u0000\u0000\u0000\u0189\u018a\u0005c\u0000"+
		"\u0000\u018a\u018b\u0005l\u0000\u0000\u018b\u018c\u0005a\u0000\u0000\u018c"+
		"\u018d\u0005s\u0000\u0000\u018d\u018e\u0005s\u0000\u0000\u018en\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0005e\u0000\u0000\u0190\u0191\u0005x\u0000"+
		"\u0000\u0191\u0192\u0005t\u0000\u0000\u0192\u0193\u0005e\u0000\u0000\u0193"+
		"\u0194\u0005n\u0000\u0000\u0194\u0195\u0005d\u0000\u0000\u0195\u0196\u0005"+
		"s\u0000\u0000\u0196p\u0001\u0000\u0000\u0000\u0197\u0198\u0005p\u0000"+
		"\u0000\u0198\u0199\u0005r\u0000\u0000\u0199\u019a\u0005o\u0000\u0000\u019a"+
		"\u019b\u0005t\u0000\u0000\u019b\u019c\u0005e\u0000\u0000\u019c\u019d\u0005"+
		"c\u0000\u0000\u019d\u019e\u0005t\u0000\u0000\u019e\u019f\u0005e\u0000"+
		"\u0000\u019f\u01a0\u0005d\u0000\u0000\u01a0r\u0001\u0000\u0000\u0000\u01a1"+
		"\u01a2\u0005n\u0000\u0000\u01a2\u01a3\u0005u\u0000\u0000\u01a3\u01a4\u0005"+
		"l\u0000\u0000\u01a4\u01a5\u0005l\u0000\u0000\u01a5t\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a7\u0005i\u0000\u0000\u01a7\u01a8\u0005n\u0000\u0000\u01a8"+
		"\u01a9\u0005s\u0000\u0000\u01a9\u01aa\u0005t\u0000\u0000\u01aa\u01ab\u0005"+
		"a\u0000\u0000\u01ab\u01ac\u0005n\u0000\u0000\u01ac\u01ad\u0005c\u0000"+
		"\u0000\u01ad\u01ae\u0005e\u0000\u0000\u01ae\u01af\u0005o\u0000\u0000\u01af"+
		"\u01b0\u0005f\u0000\u0000\u01b0v\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005"+
		"r\u0000\u0000\u01b2\u01b3\u0005e\u0000\u0000\u01b3\u01b4\u0005a\u0000"+
		"\u0000\u01b4\u01b5\u0005d\u0000\u0000\u01b5\u01b6\u0005I\u0000\u0000\u01b6"+
		"\u01b7\u0005n\u0000\u0000\u01b7\u01b8\u0005t\u0000\u0000\u01b8x\u0001"+
		"\u0000\u0000\u0000\u01b9\u01ba\u0005r\u0000\u0000\u01ba\u01bb\u0005e\u0000"+
		"\u0000\u01bb\u01bc\u0005a\u0000\u0000\u01bc\u01bd\u0005d\u0000\u0000\u01bd"+
		"\u01be\u0005F\u0000\u0000\u01be\u01bf\u0005l\u0000\u0000\u01bf\u01c0\u0005"+
		"o\u0000\u0000\u01c0\u01c1\u0005a\u0000\u0000\u01c1\u01c2\u0005t\u0000"+
		"\u0000\u01c2z\u0001\u0000\u0000\u0000\u01c3\u01c4\u0005a\u0000\u0000\u01c4"+
		"\u01c5\u0005s\u0000\u0000\u01c5\u01c6\u0005m\u0000\u0000\u01c6|\u0001"+
		"\u0000\u0000\u0000\u01c7\u01cb\u0003\u0005\u0002\u0000\u01c8\u01cb\u0003"+
		"\u0007\u0003\u0000\u01c9\u01cb\u0007\b\u0000\u0000\u01ca\u01c7\u0001\u0000"+
		"\u0000\u0000\u01ca\u01c8\u0001\u0000\u0000\u0000\u01ca\u01c9\u0001\u0000"+
		"\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000"+
		"\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd~\u0001\u0000\u0000"+
		"\u0000\u01ce\u01cf\u0005#\u0000\u0000\u01cf\u01d0\u0005i\u0000\u0000\u01d0"+
		"\u01d1\u0005n\u0000\u0000\u01d1\u01d2\u0005c\u0000\u0000\u01d2\u01d3\u0005"+
		"l\u0000\u0000\u01d3\u01d4\u0005u\u0000\u0000\u01d4\u01d5\u0005d\u0000"+
		"\u0000\u01d5\u01d6\u0005e\u0000\u0000\u01d6\u01da\u0001\u0000\u0000\u0000"+
		"\u01d7\u01d9\u0005 \u0000\u0000\u01d8\u01d7\u0001\u0000\u0000\u0000\u01d9"+
		"\u01dc\u0001\u0000\u0000\u0000\u01da\u01d8\u0001\u0000\u0000\u0000\u01da"+
		"\u01db\u0001\u0000\u0000\u0000\u01db\u01dd\u0001\u0000\u0000\u0000\u01dc"+
		"\u01da\u0001\u0000\u0000\u0000\u01dd\u01de\u0005\"\u0000\u0000\u01de\u01df"+
		"\u0003}>\u0000\u01df\u01e0\u0005\"\u0000\u0000\u01e0\u01e1\u0006?\u0002"+
		"\u0000\u01e1\u0080\u0001\u0000\u0000\u0000\u01e2\u01e5\u0003\u0013\t\u0000"+
		"\u01e3\u01e5\u0003\u0019\f\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000\u01e4"+
		"\u01e3\u0001\u0000\u0000\u0000\u01e5\u0082\u0001\u0000\u0000\u0000\u01e6"+
		"\u01e9\u00050\u0000\u0000\u01e7\u01e9\u0003\t\u0004\u0000\u01e8\u01e6"+
		"\u0001\u0000\u0000\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ea"+
		"\u0001\u0000\u0000\u0000\u01ea\u01e8\u0001\u0000\u0000\u0000\u01ea\u01eb"+
		"\u0001\u0000\u0000\u0000\u01eb\u0084\u0001\u0000\u0000\u0000\u01ec\u01ef"+
		"\u0003\u0005\u0002\u0000\u01ed\u01ef\u0007\t\u0000\u0000\u01ee\u01ec\u0001"+
		"\u0000\u0000\u0000\u01ee\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f0\u01ee\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001"+
		"\u0000\u0000\u0000\u01f1\u01f7\u0001\u0000\u0000\u0000\u01f2\u01f6\u0003"+
		"\u0005\u0002\u0000\u01f3\u01f6\u0003\u0007\u0003\u0000\u01f4\u01f6\u0007"+
		"\t\u0000\u0000\u01f5\u01f2\u0001\u0000\u0000\u0000\u01f5\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f5\u01f4\u0001\u0000\u0000\u0000\u01f6\u01f9\u0001\u0000"+
		"\u0000\u0000\u01f7\u01f5\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001\u0000"+
		"\u0000\u0000\u01f8\u0086\u0001\u0000\u0000\u0000\u01f9\u01f7\u0001\u0000"+
		"\u0000\u0000\u01fa\u01fc\u0005\n\u0000\u0000\u01fb\u01fa\u0001\u0000\u0000"+
		"\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000\u01fd\u01fb\u0001\u0000\u0000"+
		"\u0000\u01fd\u01fe\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000"+
		"\u0000\u01ff\u0200\u0006C\u0003\u0000\u0200\u0088\u0001\u0000\u0000\u0000"+
		"\u0201\u0202\u0005\t\u0000\u0000\u0202\u0203\u0006D\u0004\u0000\u0203"+
		"\u008a\u0001\u0000\u0000\u0000\u0204\u0205\u0005\r\u0000\u0000\u0205\u0206"+
		"\u0006E\u0005\u0000\u0206\u008c\u0001\u0000\u0000\u0000\u0207\u0208\u0005"+
		" \u0000\u0000\u0208\u0209\u0006F\u0006\u0000\u0209\u008e\u0001\u0000\u0000"+
		"\u0000\u020a\u020d\u0003\u008dF\u0000\u020b\u020d\u0003\u0087C\u0000\u020c"+
		"\u020a\u0001\u0000\u0000\u0000\u020c\u020b\u0001\u0000\u0000\u0000\u020d"+
		"\u020e\u0001\u0000\u0000\u0000\u020e\u020c\u0001\u0000\u0000\u0000\u020e"+
		"\u020f\u0001\u0000\u0000\u0000\u020f\u0210\u0001\u0000\u0000\u0000\u0210"+
		"\u0211\u0006G\u0007\u0000\u0211\u0090\u0001\u0000\u0000\u0000\u001d\u0000"+
		"\u009e\u00a3\u00b1\u00b5\u00bc\u00c2\u00cc\u0111\u011f\u0124\u012e\u0130"+
		"\u013c\u013e\u0142\u01ca\u01cc\u01da\u01e4\u01e8\u01ea\u01ee\u01f0\u01f5"+
		"\u01f7\u01fd\u020c\u020e\b\u0001$\u0000\u0001%\u0001\u0001?\u0002\u0001"+
		"C\u0003\u0001D\u0004\u0001E\u0005\u0001F\u0006\u0001G\u0007";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}