import java.util.ArrayList;

public class AlphaBetaSearcher implements GameTreeSearcher{

	int depthLimit;
	int nodeCount = 0;
	int bestMove = GameNode.UNDEFINED_MOVE;
	public AlphaBetaSearcher(int depth)
	{
		this.depthLimit = depth;
		
	}
	
	public double AlphaBetaEval(GameNode node, int depthLeft)
	{
		double eval = maxValue(node, depthLeft, Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
		return eval;
	}
	
	public double maxValue(GameNode node, int depthLeft,Double alpha, Double beta)
	{
		int localBestMove = GameNode.UNDEFINED_MOVE;
		if(node.gameOver() || depthLeft == 0)
		{
			return node.utility();
		}
		double res = Double.NEGATIVE_INFINITY;
		ArrayList<GameNode> children = node.expand();
		for(GameNode child: children)
		{
			double eval = minValue(child, depthLeft -1 , alpha,beta);
			if(eval > res)
			{
				res = eval;
				localBestMove = child.prevMove;
				alpha = Math.max(alpha, res);
			}
			if(res >= beta)
			{
				bestMove = localBestMove;
				return res;
			}
		}
		bestMove = localBestMove;
		return res;
	}
	public double minValue(GameNode node, int depthLeft,Double alpha, Double beta)
	{
		int localBestMove = GameNode.UNDEFINED_MOVE;
		if(node.gameOver() || depthLeft == 0)
		{
			return node.utility();
		}
		double res = Double.POSITIVE_INFINITY;
		ArrayList<GameNode> children = node.expand();
		for(GameNode child: children)
		{
			double eval = minValue(child, depthLeft -1 , alpha,beta);
			if(eval < res)
			{
				res = eval;
				localBestMove = child.prevMove;
				beta = Math.min(beta, res);
			}
			if(res <= alpha)
			{
				bestMove = localBestMove;
				return res;
			}
		}
		bestMove = localBestMove;
		return res;
	}
	@Override
	public double eval(GameNode node) {
		// TODO Auto-generated method stub
		nodeCount = 0;
		return AlphaBetaEval(node,depthLimit);
	}

	@Override
	public int getBestMove() {
		// TODO Auto-generated method stub
		return bestMove;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return nodeCount;
	}

}
